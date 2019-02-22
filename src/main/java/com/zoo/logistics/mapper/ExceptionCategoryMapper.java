package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.ExceptionCategory;

import java.util.List;

public interface ExceptionCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExceptionCategory record);

    int insertSelective(ExceptionCategory record);

    ExceptionCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExceptionCategory record);

    int updateByPrimaryKey(ExceptionCategory record);

    List<ExceptionCategory> selectAllCategory();

}