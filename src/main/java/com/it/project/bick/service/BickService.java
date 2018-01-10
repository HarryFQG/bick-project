package com.it.project.bick.service;

import com.it.project.bick.entity.BickLocation;
import com.it.project.common.exception.BickException;
import com.it.project.user.entity.UserElement;

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

    /**
     * 解锁单车
     * @param currentUser
     * @param bickNumber
     */
    void unLockBike(UserElement currentUser, Long bickNumber) throws BickException;

    void lockBike(BickLocation bickLocation) throws BickException;
    /**
     * 坐标上报
     * @param bikeLocation
     * @throws BickException
     */
    void reportLocation(BickLocation bikeLocation) throws BickException;

}
