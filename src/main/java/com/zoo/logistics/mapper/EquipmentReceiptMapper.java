package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.EquipmentReceipt;

import java.util.List;

public interface EquipmentReceiptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EquipmentReceipt record);

    int insertSelective(EquipmentReceipt record);

    EquipmentReceipt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EquipmentReceipt record);

    int updateByPrimaryKey(EquipmentReceipt record);

    EquipmentReceipt selectByCarLicenseNumber(String carLicenseNuber);
}