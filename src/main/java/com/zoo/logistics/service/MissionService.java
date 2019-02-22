package com.zoo.logistics.service;

import com.zoo.logistics.entity.Mission;
import com.zoo.logistics.mapper.MissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    @Autowired
    private MissionMapper missionMapper;

    /**
     * 根据快递员账号查找其下的任务
     * @param account
     * @return
     */
    public List selectByAccount(String account){
        return missionMapper.selectByAccount(account);
    }

    public void updateMissionStatus(String orderId){
        missionMapper.updateMissionStatus(orderId);
    }

    /**
     * 批量插入mission
     * @param orders 订单号数组
     * @param account 快递员账号
     */
    public void batchInsertMission(String[] orders,String account) {
        for(String each:orders){
            missionMapper.insert(new Mission(each,account,0));
        }
    }
}
