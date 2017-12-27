package com.it.project.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 配置redis的连接参数的类
 * @author fengqigui
 * @Date 2017/12/21 20:56
 */
@Component
public class Parameters {

    /*--redis config start--*/
    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private int redisPort;
    @Value("${redis.max-idle}")
    private int redsiMaxIdle;
    @Value("${redis.max-total}")
    private int redisMaxTotal;
    @Value("${redis.max-wait-mills}")
    private int redisMaxWaitMills;
    /*--redis config end--*/


    /*security config start*/
    @Value("#{'${securit.noneSecurityPath}'.split(',')}")
    private List<String> noneSecurityPath;



    /*security config end*/





    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public int getRedsiMaxIdle() {
        return redsiMaxIdle;
    }

    public void setRedsiMaxIdle(int redsiMaxIdle) {
        this.redsiMaxIdle = redsiMaxIdle;
    }

    public int getRedisMaxTotal() {
        return redisMaxTotal;
    }

    public void setRedisMaxTotal(int redisMaxTotal) {
        this.redisMaxTotal = redisMaxTotal;
    }

    public int getRedisMaxWaitMills() {
        return redisMaxWaitMills;
    }

    public void setRedisMaxWaitMills(int redisMaxWaitMills) {
        this.redisMaxWaitMills = redisMaxWaitMills;
    }

    public List<String> getNoneSecurityPath() {
        return noneSecurityPath;
    }

    public void setNoneSecurityPath(List<String> noneSecurityPath) {
        this.noneSecurityPath = noneSecurityPath;
    }
}
