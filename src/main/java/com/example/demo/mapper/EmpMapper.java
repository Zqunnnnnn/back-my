package com.example.demo.mapper;

import com.example.demo.bean.Emp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Zqunnnnnn
* @description 针对表【emp】的数据库操作Mapper
* @createDate 2023-11-16 14:51:06
* @Entity com.example.demo.bean.Emp
*/
public interface EmpMapper extends BaseMapper<Emp> {

//    @Select("SELECT * FROM emp where emp_name like #{empName} and emp_age like #{empAgeString} LIMIT #{pageNum},#{pageSize}")
//    List<Emp> selectPage2(Integer pageNum, Integer pageSize, String empName, String empAgeString);
//
//    @Select("SELECT count(*) FROM emp where emp_name like #{empName} and emp_age like #{empAgeString}")
//    Integer selectTotal(String empName, String empAgeString);
}




