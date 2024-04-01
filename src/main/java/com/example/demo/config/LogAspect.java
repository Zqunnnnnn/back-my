package com.example.demo.config;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.bean.Emp;
import com.example.demo.bean.Log;
import com.example.demo.exception.LoginException;
import com.example.demo.service.ILogService;
import com.example.demo.utils.Result;
import com.example.demo.utils.TokenUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Resource
    private ILogService logService;
    @Around("@annotation(autoLog)")
    public Object doAround(ProceedingJoinPoint joinPoint,AutoLog autoLog) throws Throwable{
        //注解旁边定义的内容，就是日志的内容
        String name = autoLog.value();
        //获取操作的时间
        String time = DateUtil.now();
        //获取当前操作人id
        String empId = TokenUtils.getCurrentEmp();
        if(StrUtil.isBlank(empId)){
            throw new LoginException("400","无法获取用户编号");
        }
        int empId2 = Integer.parseInt(empId);
        //获取当前操作人ip
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        //执行具体接口
        Result proceed = (Result) joinPoint.proceed();
        //往日志表里去写日志记录
        Log log = new Log();
        log.setEmpId(empId2);
        log.setIp(ip);
        log.setTime(time);
        log.setName(name);
        logService.saveOrUpdate(log);
        return proceed;
    }
}
