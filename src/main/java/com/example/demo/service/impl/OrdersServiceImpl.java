package com.example.demo.service.impl;

import com.example.demo.bean.Orders;
import com.example.demo.mapper.OrdersMapper;
import com.example.demo.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Orders> getOverOrders(LocalDateTime now) {
        List<Orders> overOrders = new ArrayList<>();
        List<Orders> list = list();
        for (Orders order : list) {
            LocalDateTime endDate = order.getEndDate();
            if(endDate.isBefore(now)){
                overOrders.add(order);
            }
        }
        return overOrders;
    }

    @Override
    public void deleteAll(List<Orders> overOrders) {
        List<Integer> ids = overOrders.stream().map(Orders::getId).collect(Collectors.toList());
        removeBatchByIds(ids);
    }
}
