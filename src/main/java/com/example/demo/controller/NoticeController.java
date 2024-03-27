package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.example.demo.utils.Result;
import com.example.demo.service.INoticeService;
import com.example.demo.bean.Notice;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zqu
 * @since 2024-03-27
 */
@RestController
@CrossOrigin
@RequestMapping("/notice")
public class NoticeController {
        @Resource
        private INoticeService noticeService;

        @PostMapping
        public Result save(@RequestBody Notice notice) {
                noticeService.saveOrUpdate(notice);
                return Result.success();
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                noticeService.removeById(id);
                return Result.success();
             }

        @GetMapping
        public Result findAll() {
                return Result.success(noticeService.list());
                }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(noticeService.list());
                }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(noticeService.page(new Page<>(pageNum,pageSize),queryWrapper));
                }
        @PostMapping("/deletes")
        public Result deleteEmps(@RequestBody List<Integer> Ids){
                return Result.success(noticeService.removeByIds(Ids));
                }


}
