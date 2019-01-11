package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StationDao {

    @Select("select * from station where id = #{id}")
    public Station getStationById(long id);

}
