package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.example.demo.utils.Result;
import com.example.demo.service.ILogService;
import com.example.demo.bean.Log;


import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zqu
 * @since 2024-04-01
 */
@RestController
@RequestMapping("/log")
public class LogController {
        @Resource
        private ILogService logService;

        @PostMapping
        public Result save(@RequestBody Log log) {
                logService.saveOrUpdate(log);
                return Result.success();
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                logService.removeById(id);
                return Result.success();
             }

        @GetMapping
        public Result findAll() {
                return Result.success(logService.list());
                }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(logService.list());
                }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize,@RequestParam String name) {
                QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
                queryWrapper.like("name",name);
                return Result.success(logService.page(new Page<>(pageNum,pageSize),queryWrapper));
                }
        @PostMapping("/deletes")
        public Result deleteEmps(@RequestBody List<Integer> Ids){
                return Result.success(logService.removeByIds(Ids));
                }


}
