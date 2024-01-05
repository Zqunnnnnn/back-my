package com.example.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.bean.Menu;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zqu
 * @since 2023-12-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getMenus(String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotBlank(name)){
            queryWrapper.like("name",name);
        }
        List<Menu> list = list(queryWrapper);
        List<Menu> parentMenus = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        for (Menu menu : parentMenus) {
            menu.setChildren(list.stream().filter(m-> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return parentMenus;
    }
}
