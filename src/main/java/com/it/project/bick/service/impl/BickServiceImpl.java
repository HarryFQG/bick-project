package com.it.project.bick.service.impl;

import com.it.project.bick.dao.BickMapper;
import com.it.project.bick.entity.Bick;
import com.it.project.bick.entity.BickLocation;
import com.it.project.bick.entity.BickNum;
import com.it.project.bick.service.BickService;
import com.it.project.common.exception.BickException;
import com.it.project.common.util.BaiduPushUtil;
import com.it.project.common.util.DateUtil;
import com.it.project.fee.dao.RideFeeMapper;
import com.it.project.fee.entity.RideFee;
import com.it.project.record.dao.RideRecordMapper;
import com.it.project.record.entity.RideRecord;
import com.it.project.user.dao.UserMapper;
import com.it.project.user.entity.User;
import com.it.project.user.entity.UserElement;
import com.it.project.util.RandomNumCode;
import com.it.project.wallet.dao.WalletMapper;
import com.it.project.wallet.entity.Wallet;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author fengqigui
 * @Date 2018/1/2 20:36
 */
@Service
public class BickServiceImpl implements BickService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BickServiceImpl.class);
    @Autowired
    private BickMapper bickMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RideRecordMapper rideRecordMapper;

    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RideFeeMapper rideFeeMapper;


    private static final int NOT_VERIFY = 1; // 未验证
    private static final int BIKE_UNLOCK = 2; //上锁，已有人使用单车
    private static final Byte RIDE_END = 2; // 骑行结束
    private static final int BIKE_LOCK = 1; //单车锁定
    @Transactional
    @Override
    public void generatBick() throws BickException {
        // 生成单车号
        BickNum bickNum = new BickNum();
        bickMapper.generateBickNum(bickNum);
        // 生成单车
        Bick bick = new Bick();
        bick.setBickType((byte)2);
        bick.setBickNumber(bickNum.getBickNum());
        bickMapper.insertSelective(bick);

    }

    @Override
    @Transactional
    public void unLockBike(UserElement currentUser, Long bickNumber) throws BickException {
        try {
            // 校检用户是否已经认证（实名认证，押金）
            User user = userMapper.selectByPrimaryKey(currentUser.getUserId());
            if (Objects.equals(user.getUserFlag(), NOT_VERIFY)) {
                throw new BickException("用户尚未认证");
            }
            // 检查用户是否有正在进行骑行记录
            RideRecord record = rideRecordMapper.selectRecordNotClosed((long)currentUser.getUserId());
            if (record != null) {
                throw new BickException("存在未关闭骑行订单");
            }
            // 检查用户的钱余额是否大于1
            Wallet wallet = walletMapper.selectByUserId(currentUser.getUserId());
            //wallet.getWalletRemainsum().compareTo(new BigDecimal(1)) < 0
            if (wallet.getWalletRemainsum().compareTo(1L) < 0) {
                throw new BickException("余额不足");
            }

            // 推送单车，进行解锁
            //BaiduPushUtil.pushMsgToSingleDevice(currentUser, "{\"title\":\"TEST\",\"description\":\"Hello baidu push!\"}");

            // 修改MongoDB中单车状态
            Query query = Query.query(Criteria.where("bike_no").is(bickNumber));
            Update update = Update.update("status", BIKE_UNLOCK);
            mongoTemplate.updateFirst(query, update, "bike_geo");

            // 建立骑行订单 记录骑行时间，(记录骑行轨迹，单车上报坐标)
            RideRecord rideRecord = new RideRecord();
            rideRecord.setBikeNo(bickNumber);
            String recordNo = new Date().getTime() + RandomNumCode.verCode();
            rideRecord.setRecordNo(recordNo);
            rideRecord.setStartTime(new Date());
            rideRecord.setId(currentUser.getUserId().longValue());
            rideRecordMapper.insertSelective(rideRecord);
        } catch (Exception e) {
            LOGGER.error("Fail to unlock bike", e);
            throw new BickException("解锁单车失败");
        }
    }

    /**
     * 自行车上锁
     * @param
     */
    @Override
    public void lockBike(BickLocation bickLocation) throws BickException {

        try {
            // 结束订单 计算骑行时间存订单
            RideRecord record = rideRecordMapper.selectBikeRecordOnGoing(bickLocation.getBickNum());
            if (record == null) {
                throw new BickException("骑行记录不存在");
            }
            long userId = record.getId();
            // 查询订单类型 查询计价信息（单价写活）
            Bick bike = bickMapper.selectByBikeNo(bickLocation.getBickNum());
            if (bike == null) {
                throw new BickException("单车不存在");
            }
            RideFee fee = rideFeeMapper.selectBikeTypeFee(bike.getBickType());
            if (fee == null) {
                throw new BickException("计费信息异常");
            }
            BigDecimal cost = BigDecimal.ZERO;
            record.setEndTime(new Date());
            record.setStatus(RIDE_END);
            Long min = DateUtil.getBetweenMin(new Date(), record.getStartTime());
            record.setRideTime(min.intValue());
            int minUnit = fee.getMinUnit();
            int intMin = min.intValue();
            if (intMin / minUnit == 0) {
                // 不足一个时间单位，按照一个时间单位计算
                cost = new BigDecimal(fee.getFee());
            } else if (intMin % minUnit == 0) {
                cost = new BigDecimal(fee.getFee()). multiply(new BigDecimal(intMin / minUnit));
            } else if (intMin % minUnit != 0) {
                cost = new BigDecimal(fee.getFee()).multiply(new BigDecimal((intMin / minUnit) + 1));
            }
            record.setRideCost(cost.longValue());
            rideRecordMapper.updateByPrimaryKeySelective(record);
            // 计费功能
            Wallet wallet = walletMapper.selectByUserId((int)userId);
            wallet.setWalletRemainsum(new BigDecimal(wallet.getWalletRemainsum()).subtract(cost).longValue());
            walletMapper.updateByPrimaryKeySelective(wallet);
            //更新状态,和坐标
            Query query = Query.query(Criteria.where("bike_no").is(bickLocation.getBickNum()));
            Update update = Update.update("status", BIKE_LOCK).set("location.coordinates", bickLocation.getCoordinates());;
            mongoTemplate.updateFirst(query, update, "bike_position");
        } catch (Exception e) {
            LOGGER.error("Fail to lock bike", e);
            throw new BickException("锁定单车失败");
        }


    }

    @Override
    public void reportLocation(BickLocation bikeLocation) throws BickException {
        try {
            // 数据库中查询该单车是否存在
            RideRecord record = rideRecordMapper.selectBikeRecordOnGoing(bikeLocation.getBickNum());
            if (record == null) {
                throw new BickException("骑行记录不存在");
            }
            DBObject object = mongoTemplate.getCollection("ride_contrail").findOne(new BasicDBObject("record_no", record.getBikeNo()));
            // 没有则插入，已存在则添加坐标
            if (object == null) {
                List<BasicDBObject> list = new ArrayList<>();
                BasicDBObject temp = new BasicDBObject("loc", bikeLocation.getCoordinates());
                list.add(temp);
                BasicDBObject insertObj = new BasicDBObject("record_no", record.getRecordNo())
                        .append("bike_no", record.getBikeNo())
                        .append("contrail", list);
                mongoTemplate.insert(insertObj,"ride_contrail");
            }else {
                Query query=new Query(Criteria.where("record_no").is(record.getRecordNo()));
                // contrail字段是一个数组，每个元素是JSON对象，内容为loc
                Update update=new Update().push("contrail",new BasicDBObject("loc",bikeLocation.getCoordinates()));
                mongoTemplate.updateFirst(query,update,"ride_contrail");
            }
        } catch (BickException e) {
            LOGGER.error("Fail to report location", e);
            throw new BickException("上报坐标失败");
        }
    }


}
