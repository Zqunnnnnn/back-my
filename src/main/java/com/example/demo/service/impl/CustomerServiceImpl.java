package com.example.demo.service.impl;

import com.example.demo.bean.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zqunnnnnn
 * @since 2023-12-09
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
