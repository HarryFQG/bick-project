package com.it.project.user.service;

import com.it.project.common.exception.BickException;
import com.it.project.user.entity.User;

public interface UserService {

    /**
     *
     * @param data 密文
     * @param key 经过RSA加密的AES的key
     * @return
     */
    String login(String data, String key) throws BickException;


    /**
     * 修改用户昵称
     * @param user
     */
    void modifyNickNmae(User user) throws BickException;
}
