package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.ExceptionOrder;

public interface ExceptionOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExceptionOrder record);

    int insertSelective(ExceptionOrder record);

    ExceptionOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExceptionOrder record);

    int updateByPrimaryKey(ExceptionOrder record);
}