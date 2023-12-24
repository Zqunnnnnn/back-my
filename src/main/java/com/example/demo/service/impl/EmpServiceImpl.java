package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Emp;
import com.example.demo.controller.dto.EmpDto;
import com.example.demo.exception.LoginException;
import com.example.demo.service.EmpService;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.utils.TokenUtils;
import org.apache.el.parser.Token;
import org.springframework.stereotype.Service;

/**
* @author Zqunnnnnn
* @description 针对表【emp】的数据库操作Service实现
* @createDate 2023-12-24 15:21:31
*/
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp>
    implements EmpService{

    @Override
    public EmpDto login(EmpDto empDto) {
        Emp one = getEmp(empDto);
        if(one != null){
            BeanUtil.copyProperties(one,empDto,true);
            String token = TokenUtils.generateToken(one.getEmpId(), one.getPassword());
            empDto.setToken(token);
            return empDto;
        }
        else {
            throw new LoginException("400","用户名或密码错误");
        }

    }

    @Override
    public Emp register(EmpDto empDto) {
        Emp one = getEmp(empDto);
        if(one==null){
            one=new Emp();
            BeanUtil.copyProperties(empDto,one,true);//将empDto的信息复制到我们要新建的one中
            save(one);
        }
        else {
            throw new LoginException("400","用户已存在");
        }
        return one;

    }

    private Emp getEmp(EmpDto empDto) {
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("emp_name",empDto.getEmpName())
                .eq("psw",empDto.getPassword());
        Emp one;
        try {
            one = getOne(wrapper);
            return one;

        } catch (Exception e) {
            throw new LoginException("500","系统错误");
        }
    }
}




