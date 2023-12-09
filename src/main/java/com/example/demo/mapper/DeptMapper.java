package com.example.demo.mapper;

import com.example.demo.bean.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Zqunnnnnn
* @description 针对表【dept】的数据库操作Mapper
* @createDate 2023-11-16 15:26:49
* @Entity com.example.demo.bean.Dept
*/
public interface DeptMapper extends BaseMapper<Dept> {
    public List<Dept> getDeptAllEmps();
    public Dept getDeptAllEmpsById(Integer deptId);
}




