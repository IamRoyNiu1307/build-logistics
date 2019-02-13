package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Log;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    Log selectLastLogByOrderId(String orderId);

    List<Log> selectByOrderIdDesc(String orderId);
}