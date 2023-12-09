package com.example.demo.controller;

import com.example.demo.bean.Dept;
import com.example.demo.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptMapper deptMapper;
    @GetMapping("/getDeptAllEmps")
    public List<Dept> getDeptAllEmps() {
        return deptMapper.getDeptAllEmps();
    }
    @GetMapping("/getDeptAllEmpsById/{deptId}")
    public Dept getDeptAllEmpsById(@PathVariable("deptId") Integer deptId){
        return deptMapper.getDeptAllEmpsById(deptId);
    }

}
