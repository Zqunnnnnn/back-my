package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.Dict;
import com.example.demo.config.AutoLog;
import com.example.demo.mapper.DictMapper;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.utils.Result;
import com.example.demo.service.IMenuService;
import com.example.demo.bean.Menu;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zqu
 * @since 2023-12-28
 */
@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {
        @Resource
        private IMenuService menuService;
        @Autowired
        private DictMapper dictMapper;

        @PostMapping
        @AutoLog("操作菜单信息")
        public Result save(@RequestBody Menu menu) {
                menuService.saveOrUpdate(menu);
                return Result.success();
        }

        @AutoLog("删除菜单信息")
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                menuService.removeById(id);
                return Result.success();
             }

        @GetMapping
        public Result findAll(@RequestParam String name){
                return Result.success(menuService.getMenus(name));
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.list());
                }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return Result.success(menuService.page(new Page<>(pageNum,pageSize),queryWrapper));
                }
        @PostMapping("/deletes")
        @AutoLog("删除多个菜单信息")
        public Result deleteEmps(@RequestBody List<Integer> Ids){
                return Result.success(menuService.removeByIds(Ids));
                }


        @GetMapping("/icons")
        public Result getIcon(){
                QueryWrapper<Dict> wrapper = new QueryWrapper<>();
                wrapper.like("type", Constants.ICON);
                List<Dict> icons = dictMapper.selectList(wrapper);
                return Result.success(icons);
        }



}
