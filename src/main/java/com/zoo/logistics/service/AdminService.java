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

    public Admin checkAccountAndPassword(Admin admin){
        //实际开发的写法
        Admin result = adminMapper.checkAccount(admin);
        return result;
    }
}
