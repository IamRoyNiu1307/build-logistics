package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.CarApplication;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CarApplicationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarApplication record);

    int insertSelective(CarApplication record);

    CarApplication selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarApplication record);

    int updateByPrimaryKey(CarApplication record);

    List selectAll();

    List selectAll(RowBounds rowBounds);

    //返回表中一共有多少数据
    int Count();

    //根据站点Id查询
    List selectByStationId(List stationId);

    List selectByStationId(List stationId,RowBounds rowBounds);

    //根据申请状态查询
    List selectByClassStatus(Integer statusId);

    List selectByClassStatus(Integer statusId,RowBounds rowBounds);

}