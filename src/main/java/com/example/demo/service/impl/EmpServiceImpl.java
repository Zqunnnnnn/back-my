package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Emp;
import com.example.demo.bean.Menu;
import com.example.demo.controller.dto.EmpDto;
import com.example.demo.exception.LoginException;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.SysRoleMenuMapper;
import com.example.demo.service.EmpService;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.utils.TokenUtils;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Zqunnnnnn
* @description 针对表【emp】的数据库操作Service实现
* @createDate 2023-12-24 15:21:31
*/
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp>
    implements EmpService{
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private MenuServiceImpl menuService;

    @Override
    public EmpDto login(EmpDto empDto) {
        Emp one = getEmp(empDto);
        if(one != null){
            BeanUtil.copyProperties(one,empDto,true);
            String token = TokenUtils.generateToken(one.getEmpId(), one.getPassword());
            empDto.setToken(token);
            // 通过登录者的role获取他所拥有的menuIds
            String role = one.getRole();
            Integer roleID = roleMapper.selectByFlag(role);
            List<Integer> menuIds = sysRoleMenuMapper.selectMenusByRoleId(roleID);
            //获取所有menu
            List<Menu> menus = menuService.getMenus("");
            System.out.println(menus);
            //筛选之后的menu的集合
            List<Menu> roleMenus = new ArrayList<>();
            //通过MenuIds过滤所有menu获取当前用户的menu对象们
            for (Menu menu : menus) {
                if(menuIds.contains(menu.getId())){
                    roleMenus.add(menu);
                }
                List<Menu> children = menu.getChildren();
//                for (Menu child : children) {
//                    if(!menuIds.contains(child.getId())){
//                        children.remove(child);
//                    }
//                }
                children.removeIf(child->!menuIds.contains(child.getId()));
                menu.setChildren(children);
            }
            empDto.setMenuList(roleMenus);
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




