package com.it.project.cache;

import com.it.project.user.entity.UserElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.Map;

/**
 * @author fengqigui
 * @Date 2017/12/21 21:11
 */
@Component
public class CommonCacheUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonCacheUtil.class);
    @Autowired
    private  JedisPoolWrapper jedisPoolWrapper;

    private static final String TOKEN_PREFIX = "token.";
    private static final String USER_PREFIX = "user.";

    /**
     * 存入永久的key
     *
     * @param key
     * @param value
     */
    public void cache(String key, String value) {
        Jedis jedis = getJedis();
        Assert.notNull(jedis, "jed's连接为空");
        // 选择redis第0片区   分开存放list，set类型的key,总共有16片区。默认使用第0个
        // select选择在哪里个区
        jedis.select(0);
        jedis.set(key, value);
        jedis.close();
    }

    /**
     * 获取value
     *
     * @param key
     * @return
     */
    public String getCacheValue(String key) {
        String value;
        Jedis jedis = getJedis();
        Assert.notNull(jedis, "jed's连接为空");
        value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 设置有缓存时间的key
     *
     * @param key    不会被覆盖的key 可以作为分布式的锁 外置锁
     * @param value
     * @param expiry 缓存时间  以秒为单位，过期时间
     * @return
     */
    public long cacheNxExpire(String key, String value, int expiry) {
        long result = 0L;
        Jedis jedis = getJedis();
        Assert.notNull(jedis, "jed's连接为空");
        //选择redis第0片区   分开存放list，set类型的key
        //select选择在哪个片区
        jedis.select(0);
        //setnx  不会覆盖原有的key，存在的化操作失败。没有的化才会存储
        //可以设置为分布式锁
        try {
            result = jedis.setnx(key, value);
            //expire 设置过期时间
            jedis.expire(key, expiry);
        } catch (Exception e) {
            LOGGER.error("设置错误");
        } finally {
            try {
                jedis.close();
            } catch (Exception e) {
                LOGGER.error("关闭连接异常");
            }
        }
        return result;
    }
    /**
     * 删除key
     *
     * @param key
     */
    public void delKey(String key) {
        Jedis jedis = getJedis();
        Assert.notNull(jedis, "jed's连接为空");
        //选择redis第0片区   分开存放list，set类型的key
        //select选择在哪里个区
        jedis.select(0);
        jedis.del(key);
        jedis.close();
    }

    /**
     * 获取redis连接
     *
     * @return
     */
    public  Jedis getJedis() {
        Jedis jedis = null;
        try {
            JedisPool jedisPool = jedisPoolWrapper.getJedisPool();
            if (null != jedisPool) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            LOGGER.error("jedisPool获取失败");
            throw new RuntimeException("jedisPool获取失败", e);
        }
        return jedis;
    }


    public void putTokenWhenLogin(UserElement userElement) {
        Jedis jedis = getJedis();
        Assert.notNull(jedis, "jed's连接为空");
        // 选择redis第0片区   分开存放list，set类型的key,总共有16片区。默认使用第0个
        // select选择在哪里个区
        jedis.select(0);
        // 注意redis的事务
        Transaction trans = jedis.multi();
        trans.del(TOKEN_PREFIX+userElement.getToken());
        // 存对象。redis的hash类型
        trans.hmset(TOKEN_PREFIX + userElement.getToken(), userElement.userElementToMap());
        trans.expire(TOKEN_PREFIX+userElement.getToken(),2592000);
        // 存set集合，防止用户在多台设备上登陆，
        trans.sadd(USER_PREFIX+userElement.getUserId(),userElement.getToken());
        trans.exec();
        jedis.close();

    }

    /**
     * 根据用户的token获得用户
     * @param token ： 用户的token
     * @return
     */
    public UserElement getUserByToken(String token) {
        UserElement userElement = null;
        Jedis jedis = getJedis();
        Assert.notNull(jedis, "jed's连接为空");
        // 选择redis第0片区   分开存放list，set类型的key,总共有16片区。默认使用第0个
        // select选择在哪里个区
        jedis.select(0);
        Map<String, String> map = jedis.hgetAll(TOKEN_PREFIX + token);
        if(!CollectionUtils.isEmpty(map)){
            userElement = UserElement.mapToUserElement(map);
            jedis.close();
            return userElement;
        }else {
            LOGGER.warn("fail to find cached element for token.");
        }
        jedis.close();
        return userElement;
    }

    /**
     *返回值：3：ip非法，超过当日验证的上限
     *      2：手机号码超过当日验证的上限
     *      1：验证码还没过期，
     * @param key ：电话号码
     * @param verCode ： 验证码
     * @param type ：类型（注册，等）
     * @param second ：多少时间过期
     * @param ip ：ip地址
     * 这里总共设置了两个键(key)：ip和手机号码
     */
    public int cacheForVerificationCode(String key, String verCode, String type, int second, String ip) {

        Jedis jedis = getJedis();
        Assert.notNull(jedis, "jed's连接为空");
        // 选择redis第0片区   分开存放list，set类型的key,总共有16片区。默认使用第0个
        // select选择在哪里个区
        jedis.select(0);
        String ipKey = "ip."+ip;
        if (ip == null) {
            return 3;
        }else {
            //检查同个IP发送的次数
            String ipSendCount = jedis.get(ipKey);
            try{
                if(ipSendCount != null && Integer.parseInt(ipSendCount) >= 10){
                    return 3;
                }
            }catch (NumberFormatException e){
                LOGGER.error("Fail to process ip send count", e);
                return 3;
            }
            long succ = jedis.setnx(key, verCode);
            if(succ == 0) {
                return 1;
            }
            // 检查同个手机号发送的次数
            String sendCount = jedis.get(key + "." + type);
            try{
                if(sendCount != null && Integer.parseInt(sendCount) >=10){
                    jedis.del(key);
                    return  2;
                }
            }catch (NumberFormatException e){
                LOGGER.error("Fail to process send count", e);
                jedis.del(key);
                return 2;
            }

            try{
                jedis.expire(key, second);
                long val = jedis.incr(key+"."+type);
                if (val == 1) {
                    jedis.expire(key+"."+type,86400);
                }

                jedis.incr(ipKey);
                if(val == 1){
                    jedis.expire(ipKey, 86400);
                }
            }catch (Exception e){
                LOGGER.error("Fail to cache data into redis", e);
            }

        }

        jedis.close();
        return 0;

    }
}
