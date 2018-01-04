package com.it.project.bick.service.impl;

import com.it.project.bick.entity.BickLocation;
import com.it.project.bick.entity.Point;
import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * mongo Geo 操作类
 *
 * @author fengqigui
 * @Date 2018/1/3 19:13
 */
@Service
public class BickGeoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BickGeoService.class);

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * @param collection    : 要查集合的名字
     * @param locationField ：准备查的字段
     * @param point         ： 坐标位置，经纬度
     * @param minDistance   ：最近距离
     * @param maxDistance   ： 最远距离
     * @param limit         ：每次显示几条
     * @param query         ： 查询条件
     * @param fields        ：限制显示的字段
     * @return sql：{location:{$nearSphere:{$geometry: {type:"Point",coordinates:[115.274955 , 30.835349]},$maxDistance: 50}},status:1}
     * 不能计算距离
     */
    public List<BickLocation> geoNearSphere(String collection, String locationField, Point point, long minDistance, long maxDistance, int limit, DBObject query, DBObject fields) {
        try {
            if (query == null) {
                query = new BasicDBObject();
            }
            // 拼接上面的sql语句，有一个花括号就new 一个BasicDBObject，平级的条件就append
            query.put(locationField,
                    new BasicDBObject("$nearSphere",
                            new BasicDBObject("$geometry",
                                    new BasicDBObject("type", "Point")
                                            .append("coordinates", new double[]{point.getLongitude(), point.getLatitude()}))
                                    .append("$minDistance", minDistance)
                                    .append("$maxDistance", maxDistance)

                    ));


            query.put("status", 1);
            fields = new BasicDBObject();
            List<DBObject> list = mongoTemplate.getCollection(collection).find(query, fields).limit(limit).toArray();
            List<BickLocation> result = new ArrayList<>();
            for (DBObject dbObject : list) {
                BickLocation bickLocation = new BickLocation();
                bickLocation.setBickNum((Long) dbObject.get("bick_num"));
                bickLocation.setStatus((Integer) dbObject.get("status"));
                // 先获得location字段，再在location字段中取coordinates
                BasicDBList coordinates = (BasicDBList) ((BasicDBObject) dbObject.get("location")).get("coordinates");
                Double[] temp = new Double[2];
                coordinates.toArray(temp);

                bickLocation.setCoordinates(temp);
                result.add(bickLocation);

            }
            return result;
        } catch (Exception e) {
            LOGGER.error("查找附近单车失败", e);
            throw new RuntimeException("查找附近单车失败");
        }
    }

    /**
     * @param collection  : 要查集合的名字
     * @param point       ： 坐标位置，经纬度
     * @param maxDistance ： 最远距离
     * @param limit       ：每次显示几条
     * @param query       ： 查询条件
     * @return 能计算距离
     */
    public List<BickLocation> geoNear(String collection, Point point, long maxDistance, int limit, DBObject query) {
        try {
            if (query == null) {
                query = new BasicDBObject();
            }


            List<DBObject> pipeLine = new ArrayList<>();
            BasicDBObject aggregate = new BasicDBObject("$geoNear",
                    new BasicDBObject("near", new BasicDBObject("type", "Point")
                            .append("coordinates", new double[]{point.getLongitude(), point.getLatitude()}))
                            //.append("distanceField", "distance")
                            .append("distanceField", "dist.calculated")
                            .append("query", new BasicDBObject("status", 1))
                            .append("num", limit)
                            .append("maxDistance", maxDistance)
                            .append("spherical", true)
            );
            pipeLine.add(aggregate);
            Cursor cursor = mongoTemplate.getCollection("bick_geo").aggregate(pipeLine, AggregationOptions.builder().build());
            List<BickLocation> result = new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                BickLocation location = new BickLocation();
                location.setBickNum((Long) obj.get("bick_num"));
                location.setStatus((Integer) obj.get("status"));
                // 先获得location字段，再在location字段中取coordinates
                BasicDBList coordinates = (BasicDBList) ((BasicDBObject) obj.get("location")).get("coordinates");
                Double[] temp = new Double[2];
                coordinates.toArray(temp);

                location.setCoordinates(temp);
                result.add(location);

            }
            return result;


        } catch (Exception e) {
            LOGGER.error("查找附近单车失败", e);
            throw new RuntimeException("查找附近单车失败");
        }
    }

    public List<BickLocation> get() {


        Cursor bick_geo = mongoTemplate.getCollection("bick_geo").find();

        return null;
    }


}
