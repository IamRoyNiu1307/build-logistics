package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.CarApplication;

public interface CarApplicationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarApplication record);

    int insertSelective(CarApplication record);

    CarApplication selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarApplication record);

    int updateByPrimaryKey(CarApplication record);
}