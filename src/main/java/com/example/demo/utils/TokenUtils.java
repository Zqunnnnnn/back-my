package com.example.demo.utils;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Component
public class TokenUtils {
    /**
     * 生成token
     */
    public static String generateToken(Integer empId,String password){
        return  JWT.create().withAudience(empId.toString()) // 将 EmpName,用户名 保存到 token 里面
                .withExpiresAt(DateUtil.offsetHour(new Date(),1)) //一小时后token过期
                .sign(Algorithm.HMAC256(password)); // 以 password 作为 token 的密钥
    }
    /**
     * 获取当前登录者的信息
     */
    public static String getCurrentEmp(){
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if(StrUtil.isBlank(token)){
                token = request.getParameter("token");
            }
            if(StrUtil.isBlank(token)){
                return null;
            }
            //解析token，获取用户id
            String empId = JWT.decode(token).getAudience().get(0);
            return empId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
