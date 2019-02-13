package com.zoo.logistics.service;

import com.zoo.logistics.entity.User;
import com.zoo.logistics.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据账号密码查找user实体
     * @param user user实体
     * @return 查找的user
     */
    public User selectByAccountAndPassword(User user){
        return userMapper.selectByAccountAndPassword(user);
    }


    public void updateByPrimaryKeySelectiv(User user){
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据openid查找绑定了该openid的账号并将账号的openid设为空
     * @param openid
     */
    public void setOpenidEqNullIfExist(String openid){
        User user = userMapper.selectByOpenid(openid);
        if(user!=null){
            user.setOpenid(null);
            userMapper.updateByPrimaryKey(user);
        }
    }

    /**
     * 根据openid查找绑定了该openid的账号
     * @param openid
     * @return 绑定了该openid的账号
     */
    public User selectByOpenid(String openid){
        return userMapper.selectByOpenid(openid);
    }
}
