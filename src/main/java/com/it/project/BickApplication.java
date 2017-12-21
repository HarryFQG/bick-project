package com.it.project;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @author fengqigui
 * @Date 2017/12/19 19:37
 */
@SpringBootApplication
@MapperScan("com.it.project.user.dao")
public class BickApplication {

    public static void main(String[] args) {

        SpringApplication.run(BickApplication.class,args);

    }

    /**
     * 不再使用默认的Jackson，而是使用阿里的fastjson
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        HttpMessageConverter<?> coverter = fastJsonHttpMessageConverter;
        return new HttpMessageConverters(coverter);

    }




}
