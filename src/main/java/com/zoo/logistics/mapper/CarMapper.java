package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Car;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    List<Car> getAllCarInCurrentStation(int stationId);

    Car selectByLicenseNumber(String licenseNumber);

    Car selectByEquipmentReceiptId(int equipmentReceiptId);
}