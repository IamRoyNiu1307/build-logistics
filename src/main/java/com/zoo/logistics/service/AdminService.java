package com.zoo.logistics.service;

import com.zoo.logistics.mapper.AdminMapper;
import com.zoo.logistics.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    //自动注入userDao
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 登录检验用户名密码
     * @param admin admin实体
     * @return
     */
    public Admin checkAccountAndPassword(Admin admin){
        Admin result = adminMapper.checkAccount(admin);
        return result;
    }
}
