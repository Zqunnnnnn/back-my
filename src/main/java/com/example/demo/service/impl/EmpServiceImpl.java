package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Emp;
import com.example.demo.service.EmpService;
import com.example.demo.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Zqunnnnnn
* @description 针对表【emp】的数据库操作Service实现
* @createDate 2023-11-16 14:51:06
*/
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp>
    implements EmpService{

    @Autowired
    private EmpMapper empMapper;

    public int saveOrUpdate2(Emp emp){
        if(emp.getDeptId() == null){
            return empMapper.insert(emp);
        }
        else {
            return empMapper.updateById(emp);
        }
    }

}




