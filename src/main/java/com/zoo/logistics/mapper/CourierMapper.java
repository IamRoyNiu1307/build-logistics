package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Courier;

import java.util.List;

public interface CourierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Courier record);

    int insertSelective(Courier record);

    Courier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Courier record);

    int updateByPrimaryKey(Courier record);

    Courier selectByOpenid(String openid);

    Courier selectByAccountAndPassword(Courier courier);

    List selectByStationId(int stationId);
}