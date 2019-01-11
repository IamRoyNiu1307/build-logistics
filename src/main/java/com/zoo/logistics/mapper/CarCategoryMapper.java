package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.CarCategory;

public interface CarCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarCategory record);

    int insertSelective(CarCategory record);

    CarCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarCategory record);

    int updateByPrimaryKey(CarCategory record);
}