package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.OrderCategory;

public interface OrderCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderCategory record);

    int insertSelective(OrderCategory record);

    OrderCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderCategory record);

    int updateByPrimaryKey(OrderCategory record);
}