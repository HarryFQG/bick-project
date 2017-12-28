package com.it.project.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.it.project.cache.CommonCacheUtil;
import com.it.project.common.exception.BickException;
import com.it.project.jms.SmsProcessor;
import com.it.project.securilty.AESUtil;
import com.it.project.securilty.Base64Util;
import com.it.project.securilty.MD5Util;
import com.it.project.securilty.RSAUtil;
import com.it.project.user.dao.UserMapper;
import com.it.project.user.entity.User;
import com.it.project.user.entity.UserElement;
import com.it.project.user.service.UserService;
import com.it.project.util.RandomNumCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonCacheUtil cacheUtil;

    @Autowired
    private SmsProcessor smsProcessor;

    private static final String VERIFYCODE_PREFIX = "verfiy.code";
    @Override
    public String login(String data, String key) throws BickException {

        String token = null;
        String decryptData = null;

        try{
            // 通过解密获得明文
            // 还原为RSA加密后的AES的密钥
            byte[] decode = Base64Util.decode(key);
            // 使用RSA解密，还原为AES的key
            byte[] aesKeyOfBytes = RSAUtil.decryptByPrivateKey(decode);
            decryptData = AESUtil.decrypt(data, new String(aesKeyOfBytes,"UTF-8"));
            if (null == decryptData) {
                throw new Exception();
            }
            JSONObject jsonObject = JSON.parseObject(decryptData);
            // 获得前端传过来的电话号码，和手机验证码
            String mobile = jsonObject.getString("mobile");
            String authCode = jsonObject.getString("code");
            String platform = jsonObject.getString("platform");
            if (StringUtils.isBlank(mobile) || StringUtils.isBlank(authCode)) {
                throw new Exception();
            }
            // 去redis取验证码 ，比较手机号码和验证码是否匹配，匹配说明是本人。因为redi刚好有过期时间效果
            String varCode = cacheUtil.getCacheValue(mobile);
            User user ;
            if (authCode.equals(varCode)) {
                // 手机验证码匹配
                user = userMapper.selectByMobile(mobile);
                if (null == user) {
                    // 判断用户是否存在于数据库中，如果存在，则生成token存在redis中；如果不存在则帮其注册，插入数据库

                    user = new User();
                    user.setUserMobile(mobile);
                    user.setUserPhoto(mobile);
                    userMapper.insert(user);
                }

            } else {
                throw new BickException("手机号验证码不匹配");
            }


            // 生成token存在redis中，这个token也就是session的ID，token作为key，value使用redis 的hash存User
            token = generateToken(user);
            UserElement userElement = new UserElement();
            userElement.setMobile(mobile);
            userElement.setUserId(user.getUserId());
            userElement.setToken(token);
            userElement.setPlatform(platform);
            cacheUtil.putTokenWhenLogin(userElement);

        }catch (Exception e){
            LOGGER.error("Fail to decrypt data ",e);
            throw new BickException("数据AES的key解密错误");
        }



        return token;

    }

    @Override
    public void modifyNickNmae(User user) throws BickException {

        userMapper.updateByPrimaryKeySelective(user);


    }

    @Override
    public void sendVercode(String userMobile, String ip) throws BickException {

        String verCode = RandomNumCode.verCode();
        int result = cacheUtil.cacheForVerificationCode(VERIFYCODE_PREFIX + userMobile, verCode, "reg", 60, ip);
        if(result == 1){
            LOGGER.info("当前验证码尚未过期，请稍后重试");
            throw new BickException("当前验证码尚未过期，请稍后重试");
        }else if (result == 2) {
            LOGGER.info("验证码超过当日的上限次数");
            throw new BickException("验证码超过当日的上限次数");
        }else if (result == 3) {
            LOGGER.info("验证码超过当日的上限次数",ip);
            throw new BickException(ip+"验证码超过当日的上限次数");
        }
        LOGGER.info("sending verify code {} for phone", verCode, userMobile);

        //TODO 验证通过，发送短信
        //smsProcessor.sendSmsToQueue();


    }

    /**
     * 获得MD5的加密字符串,生成token
     * @param user
     * @return
     */
    private String generateToken(User user) {

        String source = user.getUserId()+":"+user.getUserMobile()+":"+System.currentTimeMillis();
        String md5 = MD5Util.getMD5(source);
        return md5;

    }
}
