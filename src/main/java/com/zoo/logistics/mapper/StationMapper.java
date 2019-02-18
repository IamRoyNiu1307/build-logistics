package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Station;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface StationMapper {

    //根据主键id删除
    int deleteByPrimaryKey(Integer id);

    //插入station对象
    int insert(Station record);

    //插入station对象（检测）
    int insertSelective(Station record);

    //根据主键查询对应的station
    Station selectByPrimaryKey(Integer id);

    //查询所有的站点信息
    List<Station> selectAll();


    //查询所有的站点信息并且分页
    List<Station> selectAll(RowBounds rowBounds);


    //根据城市Id查询所有的station
    List<Station> selectByCityId(String CityId);

    //根据城市Id查询所有的station并分页
    List<Station> selectByCityId(String CityId, RowBounds rowBounds);

    //根据城市名称查询所有的station
    List<Station> selectByStationName(String CityName);

    //根据城市名称查询所有的station并分页
    List<Station> selectByStationName(String CityName, RowBounds rowBounds);

    //更新station
    int updateByPrimaryKeySelective(Station record);

    //更新
    int updateByPrimaryKey(Station record);
}