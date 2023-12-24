package com.example.demo.service;

import com.example.demo.bean.Emp;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.EmpDto;

/**
* @author Zqunnnnnn
* @description 针对表【emp】的数据库操作Service
* @createDate 2023-12-24 15:21:31
*/
public interface EmpService extends IService<Emp> {

    public EmpDto login(EmpDto empDto);

    public Emp register(EmpDto empDto);
}
