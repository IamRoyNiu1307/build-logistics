package com.zoo.logistics.service;

import com.zoo.logistics.entity.ExceptionCategory;
import com.zoo.logistics.mapper.ExceptionCategoryMapper;
import com.zoo.logistics.mapper.ExceptionOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExceptionCategoryService {
    @Autowired
    private ExceptionCategoryMapper exceptionCategoryMapper;

    /**
     * 查找出所有的异常原因
     * @return 所有的异常原因
     */
    public List<ExceptionCategory> selectAllExceptionCategory(){
        List<ExceptionCategory> exceptionCategoryList = exceptionCategoryMapper.selectAllCategory();
        return exceptionCategoryList;
    }

}
