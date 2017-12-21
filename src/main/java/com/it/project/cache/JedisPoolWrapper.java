package com.it.project.cache;

import com.it.project.common.constants.Parameters;
import com.it.project.common.exception.BickException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * redis 的连接池
 * @author fengqigui
 * @Date 2017/12/21 20:54
 */
@Component
public class JedisPoolWrapper {
    public static final Logger LOGGER = LoggerFactory.getLogger(JedisPoolWrapper.class);

    private JedisPool jedisPool=null;

    @Autowired
    private Parameters parameters;

    /**
     * 初始化redis连接池
     * 同时注意这个注解：他会在bean注入之前执行这个代码，相当于静态代码块
     */
    @PostConstruct
    public void init() throws BickException {

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(parameters.getRedsiMaxIdle());
            config.setMaxTotal(parameters.getRedisMaxTotal());
            config.setMaxWaitMillis(parameters.getRedisMaxWaitMills());
            jedisPool = new JedisPool(config, parameters.getRedisHost(), parameters.getRedisPort(),2000);
        } catch (Exception e) {
            LOGGER.error("Fail to initialize redis pool",e);
            throw new BickException("初始化redis异常");
        }

    }






    public JedisPool getJedisPool() {
        return jedisPool;
    }
}
