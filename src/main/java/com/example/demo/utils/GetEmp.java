package com.example.demo.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.example.demo.bean.Emp;
import com.example.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;


@Component
public class GetEmp {
    private static GetEmp getEmp;
    @Autowired
    private  EmpService empService;

    @PostConstruct
    public void init(){
        getEmp=this;
        getEmp.empService=this.empService;
    }
    public static Emp getCurrentEmp() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                return null;
            }
            //解析token，获取用户id
            String empId = JWT.decode(token).getAudience().get(0);
            return getEmp.empService.getById(Integer.parseInt(empId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
