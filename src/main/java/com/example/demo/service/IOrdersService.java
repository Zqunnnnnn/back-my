package com.example.demo.service;

import com.example.demo.bean.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zqu
 * @since 2024-03-31
 */
public interface IOrdersService extends IService<Orders> {

    List<Orders> getOverOrders(LocalDateTime now);

    void deleteAll(List<Orders> overOrders);
}
