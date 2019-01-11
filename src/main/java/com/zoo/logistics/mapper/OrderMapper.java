package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Order;
import com.zoo.logistics.entity.OrderKey;

public interface OrderMapper {
    int deleteByPrimaryKey(OrderKey key);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(OrderKey key);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}