package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Files;
import com.example.demo.service.FileService;
import com.example.demo.mapper.FileMapper;
import org.springframework.stereotype.Service;

/**
* @author Zqunnnnnn
* @description 针对表【file】的数据库操作Service实现
* @createDate 2023-12-20 15:37:30
*/
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Files>
    implements FileService{

}




