package com.it.project.user.controller;

import com.it.project.user.dao.UserMapper;
import com.it.project.user.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fengqigui
 * @Date 2017/12/19 19:46
 */

@RestController
@RequestMapping("/user")
@MapperScan("com.it.project.user.dao")
public class UserController {


    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public User hello(){
        User user = userMapper.selectByPrimaryKey(1);
        return  user;

    }


}
