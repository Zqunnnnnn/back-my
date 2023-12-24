package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.example.demo.service.ICustomerService;
import com.example.demo.bean.Customer;


import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zqu
 * @since 2023-12-09
 */
@Controller
@RequestMapping("//customer")
public class CustomerController {
    @Resource
    private ICustomerService customerService;

    @PostMapping
    public Boolean save(@RequestBody Customer customer) {
        return customerService.saveOrUpdate(customer);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return customerService.removeById(id);
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.list();
    }

    @GetMapping("/{id}")
    public List<Customer> findOne(@PathVariable Integer id) {
        return customerService.list();
    }

    @GetMapping("/page")
    public Page<Customer> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize) {
        return customerService.page(new Page<>(pageNum, pageSize));
    }


}
