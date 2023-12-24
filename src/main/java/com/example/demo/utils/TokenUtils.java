package com.example.demo.utils;


import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;


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
}
