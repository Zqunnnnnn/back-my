package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.bean.Role;
import com.example.demo.bean.SysRoleMenu;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.SysRoleMenuMapper;
import com.example.demo.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zqu
 * @since 2023-12-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Transactional// 保证下面两步同步进行
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        //先删除所有与roleId绑定的关系
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        sysRoleMenuMapper.delete(wrapper);
        // 添加新的与roleId绑定的menuIds
        for (Integer menuId : menuIds) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenuMapper.insert(sysRoleMenu);
        }

    }
}
