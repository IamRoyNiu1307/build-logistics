package com.zoo.logistics.mapper;

import com.zoo.logistics.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByAccountAndPassword(User user);

    User selectByOpenid(String openid);
}