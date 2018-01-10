package com.it.project.bick.dao;

import com.it.project.bick.entity.Bick;
import com.it.project.bick.entity.BickExample;
import java.util.List;

import com.it.project.bick.entity.BickNum;
import org.apache.ibatis.annotations.Param;

public interface BickMapper {
    int countByExample(BickExample example);

    int deleteByExample(BickExample example);

    int deleteByPrimaryKey(Long bickId);

    int insert(Bick record);

    int insertSelective(Bick record);

    List<Bick> selectByExample(BickExample example);

    Bick selectByPrimaryKey(Long bickId);

    int updateByExampleSelective(@Param("record") Bick record, @Param("example") BickExample example);

    int updateByExample(@Param("record") Bick record, @Param("example") BickExample example);

    int updateByPrimaryKeySelective(Bick record);

    int updateByPrimaryKey(Bick record);

    /**
     * 生成单车的编号
     * @param bickNum
     * @return
     */
    Long generateBickNum(BickNum bickNum);

    Bick selectByBikeNo(Long bickNumber);
}