package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Station;

import java.util.List;

public interface StationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Station record);

    int insertSelective(Station record);

    Station selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Station record);

    int updateByPrimaryKey(Station record);

    List<Station> selectByCityName(String cityName);
}