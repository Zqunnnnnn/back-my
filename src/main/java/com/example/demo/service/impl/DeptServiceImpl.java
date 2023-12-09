package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Dept;
import com.example.demo.service.DeptService;
import com.example.demo.mapper.DeptMapper;
import org.springframework.stereotype.Service;

/**
* @author Zqunnnnnn
* @description 针对表【dept】的数据库操作Service实现
* @createDate 2023-11-16 15:26:49
*/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
    implements DeptService{

}




