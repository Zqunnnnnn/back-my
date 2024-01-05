package com.example.demo.service;

import com.example.demo.bean.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zqu
 * @since 2023-12-28
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenus(String name);
}
