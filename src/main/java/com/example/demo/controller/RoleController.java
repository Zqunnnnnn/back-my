package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.SysRoleMenu;
import com.example.demo.config.AutoLog;
import com.example.demo.mapper.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.utils.Result;
import com.example.demo.service.IRoleService;
import com.example.demo.bean.Role;


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
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
        @Resource
        private IRoleService roleService;
        @Autowired
        private SysRoleMenuMapper sysRoleMenuMapper;

        @PostMapping
        @AutoLog("操作角色信息")
        public Result save(@RequestBody Role role) {
                roleService.saveOrUpdate(role);
                return Result.success();
        }

        @DeleteMapping("/{id}")
        @AutoLog("删除角色信息")
        public Result delete(@PathVariable Integer id) {
                roleService.removeById(id);
                return Result.success();
             }

        @GetMapping
        public Result findAll() {
                return Result.success(roleService.list());
                }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(roleService.list());
                }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String name) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return Result.success(roleService.page(new Page<>(pageNum,pageSize),queryWrapper));
                }
        @PostMapping("/deletes")
        @AutoLog("操作多个角色信息")
        public Result deleteEmps(@RequestBody List<Integer> Ids){
                return Result.success(roleService.removeByIds(Ids));
                }

        @PostMapping("/roleMenu/{roleId}")
        public Result setRoleMenu(@PathVariable("roleId") Integer roleId,@RequestBody List<Integer> menuIds) {
                roleService.setRoleMenu(roleId,menuIds);
                return Result.success();
        }
        @GetMapping("/getMenu/{roleId}")
        public Result findMenuByRoleId(@PathVariable("roleId") Integer roleId) {
                QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
                wrapper.eq("role_id", roleId);
                List<SysRoleMenu> data = sysRoleMenuMapper.selectList(wrapper);
                List<Integer> menuIds = data.stream().map(m -> m.getMenuId()).collect(Collectors.toList());
                return Result.success(menuIds);
        }

}
