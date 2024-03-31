package com.example.demo.service.impl;

import com.example.demo.bean.Orders;
import com.example.demo.mapper.OrdersMapper;
import com.example.demo.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zqu
 * @since 2024-03-31
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
