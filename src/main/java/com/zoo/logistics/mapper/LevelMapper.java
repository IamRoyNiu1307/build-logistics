package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Level;

public interface LevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Level record);

    int insertSelective(Level record);

    Level selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);
}