package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.OrderAmount;

public interface OrderAmountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderAmount record);

    int insertSelective(OrderAmount record);

    OrderAmount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderAmount record);

    int updateByPrimaryKey(OrderAmount record);
}