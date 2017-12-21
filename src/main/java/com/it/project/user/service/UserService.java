package com.it.project.user.service;

import com.it.project.common.exception.BickException;

public interface UserService {

    /**
     *
     * @param data 密文
     * @param key 经过RSA加密的AES的key
     * @return
     */
    String login(String data, String key) throws BickException;


}
