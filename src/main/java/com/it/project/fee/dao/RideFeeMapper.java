package com.it.project.fee.dao;

import com.it.project.fee.entity.RideFee;
import com.it.project.fee.entity.RideFeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RideFeeMapper {
    int countByExample(RideFeeExample example);

    int deleteByExample(RideFeeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RideFee record);

    int insertSelective(RideFee record);

    List<RideFee> selectByExample(RideFeeExample example);

    RideFee selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RideFee record, @Param("example") RideFeeExample example);

    int updateByExample(@Param("record") RideFee record, @Param("example") RideFeeExample example);

    int updateByPrimaryKeySelective(RideFee record);

    int updateByPrimaryKey(RideFee record);

    RideFee selectBikeTypeFee(Byte bickType);
}