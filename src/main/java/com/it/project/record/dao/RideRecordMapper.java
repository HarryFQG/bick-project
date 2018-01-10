package com.it.project.record.dao;

import com.it.project.record.entity.RideRecord;
import com.it.project.record.entity.RideRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RideRecordMapper {
    int countByExample(RideRecordExample example);

    int deleteByExample(RideRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RideRecord record);

    int insertSelective(RideRecord record);

    List<RideRecord> selectByExample(RideRecordExample example);

    RideRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RideRecord record, @Param("example") RideRecordExample example);

    int updateByExample(@Param("record") RideRecord record, @Param("example") RideRecordExample example);

    int updateByPrimaryKeySelective(RideRecord record);

    int updateByPrimaryKey(RideRecord record);
    RideRecord selectRecordNotClosed(Long userId);

    RideRecord selectBikeRecordOnGoing(Long bickNumber);

    List<RideRecord> selectRideRecordPage(Integer userId, Long lastId);
}