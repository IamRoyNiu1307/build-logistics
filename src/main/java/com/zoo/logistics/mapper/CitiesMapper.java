package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Cities;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CitiesMapper {

    //根据主键删除信息
    int deleteByPrimaryKey(Integer id);

    //插入
    int insert(Cities record);

    //插入（检测）
    int insertSelective(Cities record);

    //根据主键查询
    Cities selectByPrimaryKey(Integer id);


    //根据城市名称查询
    Cities selectByCityName(@Param("city") String cityName);

    //更新（检测）
    int updateByPrimaryKeySelective(Cities record);

    //更新
    int updateByPrimaryKey(Cities record);
}