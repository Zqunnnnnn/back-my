package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.example.demo.utils.Result;
import com.example.demo.service.IOrdersService;
import com.example.demo.bean.Orders;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zqu
 * @since 2024-03-31
 */
@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrdersController {
        @Resource
        private IOrdersService ordersService;

        @PostMapping
        public Result save(@RequestBody Orders orders) {
                ordersService.saveOrUpdate(orders);
                return Result.success();
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                ordersService.removeById(id);
                return Result.success();
             }

        @GetMapping
        public Result findAll() {
                return Result.success(ordersService.list());
                }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(ordersService.list());
                }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(ordersService.page(new Page<>(pageNum,pageSize),queryWrapper));
                }
        @PostMapping("/deletes")
        public Result deleteEmps(@RequestBody List<Integer> Ids){
                return Result.success(ordersService.removeByIds(Ids));
                }


}
