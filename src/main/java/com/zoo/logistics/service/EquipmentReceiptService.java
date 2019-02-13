package com.zoo.logistics.service;

import com.zoo.logistics.entity.Car;
import com.zoo.logistics.entity.EquipmentReceipt;
import com.zoo.logistics.mapper.CarMapper;
import com.zoo.logistics.mapper.EquipmentReceiptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EquipmentReceiptService {
    @Autowired
    private EquipmentReceiptMapper equipmentReceiptMapper;
    @Autowired
    private CarMapper carMapper;

    public EquipmentReceipt selectByPrimaryKey(int id){
        return equipmentReceiptMapper.selectByPrimaryKey(id);
    }

    /**
     * 插入(根据对象中不为空的属性)
     * @param equipmentReceipt 交接单实体
     */
    public int insertSelective(EquipmentReceipt equipmentReceipt){
        equipmentReceiptMapper.insertSelective(equipmentReceipt);
        return equipmentReceipt.getId();
    }

    /**
     * 根据主键更新交接单实体中不为空的属性
     * @param equipmentReceipt 交接单实体
     */
    public void updateByPrimaryKeySelective(EquipmentReceipt equipmentReceipt){
        equipmentReceiptMapper.updateByPrimaryKeySelective(equipmentReceipt);
    }

    /**
     * 根据车牌号，查找当前车辆绑定的交接单
     * @param licenseNumber 车牌号
     * @param destinationStationId 正在驶往的站点id
     * @return
     */
    public Map selectEquipmentReceiptByLicenseNumber(String licenseNumber,int destinationStationId){
        Map resultMap = new HashMap();
        Car car = carMapper.selectByLicenseNumber(licenseNumber);
        //判断车辆的状态是否为2或4
        //如果是2 则说明车辆在途中，正在驶往的站点是终点站点
        //如果是4 则说明车辆在返程，正在驶往的站点是起始站点
        if((car.getCarStatus()==2&&car.getRoute().getEndStationId()==destinationStationId)
                || (car.getCarStatus()==4&&car.getRoute().getStartStationId()==destinationStationId)){
            EquipmentReceipt equipmentReceipt = equipmentReceiptMapper.selectByCarLicenseNumber(car.getLicenseNumber());
            resultMap.put("status",1);
            resultMap.put("msg","查找成功");
            resultMap.put("resource",car.getCarStatus()==2?car.getRoute().getStartStation().getStationName():car.getRoute().getEndStation().getStationName());
            resultMap.put("equipmentReceipt",equipmentReceipt);
        }else{
            resultMap.put("status",0);
            resultMap.put("msg","暂无交接单信息");
        }
        return resultMap;
    }
}
