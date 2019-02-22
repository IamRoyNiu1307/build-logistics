package com.zoo.logistics.service;

import com.zoo.logistics.entity.Courier;
import com.zoo.logistics.mapper.CourierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {

    @Autowired
    private CourierMapper courierMapper;

    public Courier selectByOpenid(String openid){
        return courierMapper.selectByOpenid(openid);
    }

    /**
     * 根据账号密码检验账号
     * @param courier 快递员账号
     * @return
     */
    public Courier selectByAccountAndPassword(Courier courier) {
        return courierMapper.selectByAccountAndPassword(courier);
    }

    /**
     * 根据openid查找绑定了该openid的账号并将账号的openid设为空
     * @param openid
     */
    public void setOpenidEqNullIfExist(String openid) {
        Courier courier = courierMapper.selectByOpenid(openid);
        if(courier!=null){
            courier.setOpenid(null);
            courierMapper.updateByPrimaryKey(courier);
        }
    }

    /**
     * 根据站点id查找该站点下的快递员
     * @param stationId
     * @return
     */
    public List selectCourierByStationId(int stationId){
        return courierMapper.selectByStationId(stationId);
    }

    public void updateByPrimaryKeySelectiv(Courier courier) {
        courierMapper.updateByPrimaryKeySelective(courier);
    }
}
