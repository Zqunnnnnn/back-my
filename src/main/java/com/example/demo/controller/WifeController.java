package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.example.demo.service.IWifeService;
import com.example.demo.bean.Wife;


import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zqu
 * @since 2023-12-09
 */
@RestController
@RequestMapping("//wife")
public class WifeController {
        @Resource
        private IWifeService wifeService;

        @PostMapping
        public Boolean save(@RequestBody Wife wife) {
                return wifeService.saveOrUpdate(wife);
                }

        @DeleteMapping("/{id}")
        public Boolean delete(@PathVariable Integer id) {
                return wifeService.removeById(id);
             }

        @GetMapping
        public List<Wife> findAll() {
                return wifeService.list();
                }

        @GetMapping("/{id}")
        public List<Wife> findOne(@PathVariable Integer id) {
                return wifeService.list();
                }

        @GetMapping("/page")
        public Page<Wife> findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize) {
                return wifeService.page(new Page<>(pageNum, pageSize));
                }


}
