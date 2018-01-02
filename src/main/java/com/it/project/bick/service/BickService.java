package com.it.project.bick.service;

import com.it.project.common.exception.BickException;

/**
 * @author fengqigui
 * @Date 2018/1/2 20:34
 */
public interface BickService {

    /**
     * 用于生成共享单车的编号
     * @throws BickException
     */
    void generatBick()throws BickException;

}
