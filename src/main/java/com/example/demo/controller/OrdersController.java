package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.Room;
import com.example.demo.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
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
        public Result delete(@PathVariable Integer id) {
                Orders order = ordersService.getById(id);
                Integer roomId = order.getRoomId();
                Room room = roomService.getById(roomId);
                room.setStatus(true);
                roomService.saveOrUpdate(room);
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
        @RequestParam Integer pageSize,@RequestParam (required = false) Integer id) {
                QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
                if(id!=null) {
                        queryWrapper.eq("id", id);
                }
                else {

                }
                return Result.success(ordersService.page(new Page<>(pageNum,pageSize),queryWrapper));
                }
        @PostMapping("/deletes")
        public Result deleteEmps(@RequestBody List<Integer> Ids){
                return Result.success(ordersService.removeByIds(Ids));
                }


        @GetMapping("/getRoom")
        public Result getRoom(){
                QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("status",true);
                List<Room> rooms = roomService.list(queryWrapper);
                return Result.success(rooms);
        }
}
