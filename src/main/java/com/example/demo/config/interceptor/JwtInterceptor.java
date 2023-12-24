package com.example.demo.config.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.bean.Emp;
import com.example.demo.exception.LoginException;
import com.example.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private EmpService empService;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        // 执行认证
        if (token == null) {
            throw new LoginException("400","无token，请重新登录");        }
        // 获取 token 中的 empId
        String empId;
        try {
            empId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new LoginException("400","token验证失败");
        }
        Emp emp = empService.getById(empId);
        if (emp == null) {
            throw new LoginException("400","未有该用户");
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(emp.getPsw())).build();
        try {
            jwtVerifier.verify(token);//验证token
        } catch (JWTVerificationException e) {
            throw new LoginException("400","未通过token验证");
        }
        return true;

    }
}
