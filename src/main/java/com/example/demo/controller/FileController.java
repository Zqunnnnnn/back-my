package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.Files;
import com.example.demo.service.impl.FileServiceImpl;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {


    @Autowired
    private FileServiceImpl fileService;
    @Value("${files.uploads.path}")
    private String fileUploadPath;

    /**
     * 前端传来文件
     * 返回最后存的地址
     */
    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long fileSize = file.getSize();
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUid = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUid);//实际最后要存的文件
        //判断目录是否存在
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        String url;
        file.transferTo(uploadFile);
        String md5 = SecureUtil.md5(uploadFile);
        Files same = isSame(md5);
        if(same!=null){
            url=same.getUrl();
            uploadFile.delete();
        }else {
            url= "http://localhost:8088/file/download/" + fileUUid;
            Files saveFile = new Files();
            saveFile.setName(fileUUid);
            saveFile.setType(type);
            saveFile.setSize(fileSize/1024);
            saveFile.setUrl(url);
            saveFile.setMd5(md5);
            saveFile.setIsDelete(false);
            saveFile.setEnable(true);
        }
        return url;
    }
    @GetMapping("/download/{fileUUid}")
    public void downloadFile(@PathVariable String fileUUid, HttpServletResponse response) throws IOException{
        //根据传过来的的唯一标识获取文件
        File uploadFile = new File(fileUploadPath + fileUUid);
        //设置出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUid,"UTF-8"));
        response.setContentType("application/octet-stream");
        //设置文件字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }
    @PostMapping("/update")
    public Result updateFile(@RequestBody Files file){
        return Result.success(fileService.saveOrUpdate(file));
    }

    @GetMapping("/page")
    public Result getPage(@RequestParam Integer pageNum,
                          @RequestParam Integer pageSize,
                          @RequestParam(required = false) String name
    ){
        //查询未被删除的文件信息，也就是说is_delete为false
        IPage<Files> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.eq("is_delete",false);
        return Result.success(fileService.page(page,queryWrapper));
    }
    @DeleteMapping("/deleteEmp/{id}")
    public Result deleteFile(@PathVariable("id") Integer id){
        Files file = fileService.getById(id);
        file.setIsDelete(true);
        return Result.success(fileService.updateById(file));
    }

    @PostMapping("/deletes")
    public Result deleteFiles(@RequestBody List<Integer> Ids){
        QueryWrapper<Files> wrapper = new QueryWrapper<>();
        wrapper.in("id",Ids);
        List<Files> list = fileService.list(wrapper);
        for (Files files : list) {
            files.setIsDelete(true);
            fileService.updateById(files);
        }
        return Result.success("Files deleted");
    }
    private Files isSame(String md5){
        QueryWrapper<Files> wrapper = new QueryWrapper<>();
        wrapper.eq("md5",md5);
        List<Files> filesList = fileService.list(wrapper);
//        List<Files> filesList = filesMapper.selectList(wrapper);
        return filesList.size()==0?null:filesList.get(0);
    }
    @GetMapping
    public Result findAll() {
        return Result.success(fileService.list());
    }
}
