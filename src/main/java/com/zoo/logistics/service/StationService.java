package com.zoo.logistics.service;

import com.zoo.logistics.entity.Station;
import com.zoo.logistics.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {
    @Autowired
    private StationMapper stationMapper;

    public Station selectByPrimaryKey(int id){
        return stationMapper.selectByPrimaryKey(id);
    }
}
