package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.example.demo.utils.Result;
import com.example.demo.service.IFixService;
import com.example.demo.bean.Fix;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zqu
 * @since 2024-02-28
 */
@RestController
@CrossOrigin
@RequestMapping("/fix")
public class FixController {
        @Resource
        private IFixService fixService;

        @PostMapping
        public Result save(@RequestBody Fix fix) {
                fixService.saveOrUpdate(fix);
                return Result.success();
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                fixService.removeById(id);
                return Result.success();
             }

        @GetMapping
        public Result findAll() {
                return Result.success(fixService.list());
                }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(fixService.list());
                }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize,@RequestParam (required = false) Integer fixId) {
        QueryWrapper<Fix> queryWrapper = new QueryWrapper<>();
        if(fixId!=null) {
                queryWrapper.eq("fix_id", fixId);
        }
        else {

        }
        return Result.success(fixService.page(new Page<>(pageNum,pageSize),queryWrapper));
                }
        @PostMapping("/deletes")
        public Result deleteEmps(@RequestBody List<Integer> Ids){
                return Result.success(fixService.removeByIds(Ids));
                }


}
