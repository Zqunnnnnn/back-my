package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import cn.hutool.core.util.StrUtil;
import com.example.demo.bean.Emp;
import com.example.demo.service.EmpService;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/echarts")
@CrossOrigin
public class EchartsController {
    @Autowired
    private EmpService empService;
    @GetMapping("/createTime")
    public Result getTime(){
        List<Emp> emps = empService.list();
        int q1=0;//第一季度
        int q2=0;
        int q3=0;
        int q4=0;
        for (Emp emp : emps) {
            Date createTime = emp.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter) {
                case Q1:q1++; break;
                case Q2:q2++; break;
                case Q3:q3++; break;
                case Q4:q4++; break;
                default:break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));
    }
}
