package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Route;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface RouteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Route record);

    int insertSelective(Route record);

    Route selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);

    //路线是否存在
    Route isexist(Route record);

    //返回表中一共有多少数据
    int RouteCount();

    //返回所有列表的信息
    List<Route> selectAll();

    //查询表中所有路线并分页
    List<Route> selectAll(RowBounds rowBounds);

    //根据起始地返回所有列表的信息
    List<Route> selectByStartArea(List startStationId);

    //根据起始地查询表中所有路线并分页
    List<Route> selectByStartArea(List startStationId,RowBounds rowBounds);

    //根据目的地返回所有列表的信息
    List<Route> selectByEndArea(List endStationId);

    //根据目的地查询表中所有路线并分页
    List<Route> selectByEndArea(List endStationId,RowBounds rowBounds);

    //根据路线返回所有列表的信息
    List<Route> selectByRoute(@Param("startStationId") List startStationId,@Param("endStationId") List endStationId);

    //根据路线查询表中所有路线并分页
    List<Route> selectByRoute(@Param("startStationId") List startStationId,@Param("endStationId") List endStationId,RowBounds rowBounds);


}