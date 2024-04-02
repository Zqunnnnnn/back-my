package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.Room;
import com.example.demo.config.AutoLog;
import com.example.demo.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

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
        @Autowired
        private IRoomService roomService;

        @PostMapping
        @AutoLog("订单操作")
        public Result save(@RequestBody Orders orders) {
                Integer roomId = orders.getRoomId();
                for (Room room : roomService.list()) {
                        if(room.getId()==roomId){
                                room.setStatus(false);
                                roomService.saveOrUpdate(room);
                        }
                }
                int days = (int)ChronoUnit.DAYS.between(orders.getOrderDate(), orders.getEndDate());
                if(days<0){
                      return Result.error("开始时间不得晚于结束时间");
                }
                else {
                        //得到对应房间价格
                        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("id", roomId);
                        Double price = roomService.getOne(queryWrapper).getPrice();
                        orders.setTotalPrice(price * days);
                }
                ordersService.saveOrUpdate(orders);
                return Result.success();
        }

        @DeleteMapping("/{id}")
        @AutoLog("删除订单信息")
        public Result delete(@PathVariable Integer id) {
                Orders order = ordersService.getById(id);
                Integer roomId = order.getRoomId();
                Room room = roomService.getById(roomId);
                room.setStatus(true);
                roomService.saveOrUpdate(room);
                ordersService.removeById(id);
                return Result.success();
             }
        @PostMapping("/deletes")
        @AutoLog("删除多个订单信息")
        public Result deleteEmps(@RequestBody List<Integer> Ids){
                List<Orders> orders = ordersService.listByIds(Ids);
                for (Orders order : orders) {
                        Integer roomId = order.getRoomId();
                        Room room = roomService.getById(roomId);
                        room.setStatus(true);
                        roomService.saveOrUpdate(room);
                }
                return Result.success(ordersService.removeByIds(Ids));
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
        @RequestParam Integer pageSize,@RequestParam (required = false) Integer id) {
                QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
                if(id!=null) {
                        queryWrapper.eq("id", id);
                }
                else {

                }
                return Result.success(ordersService.page(new Page<>(pageNum,pageSize),queryWrapper));
                }



        @GetMapping("/getRoom")
        public Result getRoom(){
                QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("status",true);
                List<Room> rooms = roomService.list(queryWrapper);
                return Result.success(rooms);
        }
        /**
         * 创建定时任务，当到达结束日期之后删除所属订单
         */
        @Scheduled(fixedRate = 60000)
        @AutoLog("到期删除订单信息")
        public void scheduleOrderClean(){
                LocalDateTime now = LocalDateTime.now();
                List<Orders> overOrders = ordersService.getOverOrders(now);
                if (!overOrders.isEmpty()) {
                        ordersService.deleteAll(overOrders);
                }
        }
}
