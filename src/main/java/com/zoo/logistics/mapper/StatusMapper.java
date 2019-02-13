package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Station;
import com.zoo.logistics.entity.Status;

import java.util.List;

public interface StatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Status record);

    int insertSelective(Status record);

    Status selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Status record);

    int updateByPrimaryKey(Status record);


}