package com.example.demo.service.impl;

import com.example.demo.bean.Log;
import com.example.demo.mapper.LogMapper;
import com.example.demo.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zqu
 * @since 2024-04-01
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
