package com.example.demo.controller.dto;

import com.example.demo.bean.Menu;
import com.example.demo.utils.TokenUtils;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 接受前端的登陆参数
 * 员工编号
 * 员工姓名（密码）
 */
@Data
public class EmpDto {
    private String empName;//账号
    private String password;//密码
    private String empSex;
    private String token;
    private String avatarUrl;
    private String role;
    private List<Menu> menuList;

}
