package com.it.project.bick.service.impl;

import com.it.project.bick.dao.BickMapper;
import com.it.project.bick.entity.Bick;
import com.it.project.bick.entity.BickNum;
import com.it.project.bick.service.BickService;
import com.it.project.common.exception.BickException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fengqigui
 * @Date 2018/1/2 20:36
 */
@Service
public class BickServiceImpl implements BickService {

    @Autowired
    private BickMapper bickMapper;

    @Transactional
    @Override
    public void generatBick() throws BickException {
        // 生成单车号
        BickNum bickNum = new BickNum();
        bickMapper.generateBickNum(bickNum);
        // 生成单车
        Bick bick = new Bick();
        bick.setBickType((byte)2);
        bick.setBickNumber(bickNum.getBickNum());
        bickMapper.insertSelective(bick);

    }


}
