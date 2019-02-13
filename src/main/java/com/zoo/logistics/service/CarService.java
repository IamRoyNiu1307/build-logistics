package com.zoo.logistics.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoo.logistics.entity.Car;
import com.zoo.logistics.mapper.CarMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;

    /**
     * 获取当前站点内所有的车辆
     * @param stationId 站点id
     * @param pageNum 分页的当前页码
     * @param pageSize 每页的数据数
     * @return pageInfo
     */
    public PageInfo getAllCarInCurrentStation(int stationId,int pageNum,int pageSize){
//        List<Car> allCarInCurrentStation = carMapper.getAllCarInCurrentStation(stationId,new RowBounds(start,limit));
        PageHelper.startPage(pageNum, pageSize);
        List<Car> allCarInCurrentStation = carMapper.getAllCarInCurrentStation(stationId);
        PageInfo pageInfo = new PageInfo(allCarInCurrentStation);
        return pageInfo;

    }

    /**
     * 更新车辆的状态为运输中(将状态id更改为2或4)
     * @param licenseNumber 车牌号
     */
    public void updateCarStatus(String licenseNumber){
        Car car = carMapper.selectByLicenseNumber(licenseNumber);
        car.setCarStatus(car.getCarStatus()+1);
        carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 根据交接单id查找该交接单所在的车辆
     * @param equipmentReceiptId 交接单id
     * @return 绑定了该交接单的车辆
     */
    public Car selectByEquipmentReceiptId(int equipmentReceiptId){
        return carMapper.selectByEquipmentReceiptId(equipmentReceiptId);
    }

    public void updateByPrimaryKeySelective(Car car){
        carMapper.updateByPrimaryKeySelective(car);
    }
}
