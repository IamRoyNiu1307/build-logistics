package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Car;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface CarMapper {
    //删除车辆（int id）
    int deleteByPrimaryKey(Integer id);

    //添加车辆（car实体类）
    int insert(Car record);

    //添加车辆（非空检测）
    int insertSelective(Car record);

    //查询车辆-通过主键（int id）
    Car selectByPrimaryKey(Integer id);

    //查询表中所有的车辆
    List<Car> selectAll();

    //查询表中所有车辆并分页
    List<Car> selectAll(RowBounds rowBounds);

    //查询表中所有符合条件的车辆-车牌号（string licenseNumber）
    List<Car> selectBylicenseNumber(@Param("licenseNumber") String licenseNumber);

    //查询表中所有符合条件的车辆-车牌号（string licenseNumber）并分页
    List<Car> selectBylicenseNumber(@Param("licenseNumber") String licenseNumber,RowBounds rowBounds);

    //查询表中所有符合条件的车辆-车辆状态（int carStatus）
    List<Car> selectBycarStatus(Map map);

    //查询表中所有符合条件的车辆-车辆状态（int carStatus）并分页
    List<Car> selectBycarStatus(Map map,RowBounds rowBounds);

    //查询表中所有符合条件的车辆-车辆类型（int carCategoryId）
    List<Car> selectBycarCategoryId(Integer id);

    //查询表中所有符合条件的车辆-车辆类型（int carCategoryId）并分页
    List<Car> selectBycarCategoryId(Integer id,RowBounds rowBounds);

    //通过主键更新数据（非空检测）
    int updateByPrimaryKeySelective(Car record);

    //通过主键更新数据
    int updateByPrimaryKey(Car record);

    //返回表中一共有多少数据
    int CarCount();

    //查询表中所有符合条件的车辆-车辆状态（int carStatus）
    List<Car> selectByCategoryStatus(Map map);

    //查询表中所有符合条件的车辆-车辆状态（int carStatus）并分页
    List<Car> selectByCategoryStatus(Map map,RowBounds rowBounds);
}