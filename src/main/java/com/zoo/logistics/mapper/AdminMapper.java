package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin checkAccount(Admin admin);

    //查询表中所有的用户
    List<Admin> selectAll();

    //查询表中所有用户并分页
    List<Admin> selectAll(RowBounds rowBounds);

    //返回表中一共有多少数据
    int AdminCount();

    //根据站点名称查询表中所有的用户
    List<Admin> selectByStationId(List stationId);

    //根据站点名称查询表中所有的用户并分页
    List<Admin> selectByStationId(List stationId,RowBounds rowBounds);

    //根据站点名称查询表中所有的用户
    List<Admin> selectByLevel(Integer level);

    //根据站点名称查询表中所有的用户并分页
    List<Admin> selectByLevel(Integer level,RowBounds rowBounds);


    //根据路线返回所有列表的信息
    List<Admin> selectByAll(@Param("stationId") List stationId,@Param("level") Integer level);

    //根据路线查询表中所有路线并分页
    List<Admin> selectByAll(@Param("stationId") List stationId,@Param("level") Integer level,RowBounds rowBounds);


}