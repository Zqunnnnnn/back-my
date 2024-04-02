package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.AutoLog;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.example.demo.utils.Result;
import com.example.demo.service.IRoomService;
import com.example.demo.bean.Room;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zqu
 * @since 2024-03-25
 */
@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {
        @Resource
        private IRoomService roomService;
        @Autowired
        private FileService fileService;

        @PostMapping
        @AutoLog("更新房间信息")
        public Result save(@RequestBody Room room) {
                roomService.saveOrUpdate(room);
                return Result.success();
        }

        @DeleteMapping("/{id}")
        @AutoLog("删除房间信息")
        public Result delete(@PathVariable Integer id) {
                roomService.removeById(id);
                return Result.success();
             }

        @GetMapping
        public Result findAll() {
                return Result.success(roomService.list());
                }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(roomService.list());
                }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String name) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return Result.success(roomService.page(new Page<>(pageNum,pageSize),queryWrapper));
                }
        @PostMapping("/deletes")
        @AutoLog("删除多个房间信息")
        public Result deleteEmps(@RequestBody List<Integer> Ids){
                return Result.success(roomService.removeByIds(Ids));
                }


}
