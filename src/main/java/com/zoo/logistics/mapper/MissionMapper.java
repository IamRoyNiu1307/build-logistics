package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Mission;

import java.util.List;

public interface MissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mission record);

    int insertSelective(Mission record);

    Mission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mission record);

    int updateByPrimaryKey(Mission record);

    List selectByAccount(String account);

    void updateMissionStatus(String orderId);

    int selectMissionCountByAccount(int account);

}