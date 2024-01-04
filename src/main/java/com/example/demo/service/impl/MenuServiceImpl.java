package com.example.demo.service.impl;

import com.example.demo.bean.Menu;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
