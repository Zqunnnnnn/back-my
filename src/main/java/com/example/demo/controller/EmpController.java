package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.Emp;
import com.example.demo.controller.dto.EmpDto;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.service.impl.EmpServiceImpl;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;


@RestController
@RequestMapping("/emp")
@CrossOrigin
public class EmpController {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpServiceImpl empService;

    @PostMapping("/deletes")
    public Result deleteEmps(@RequestBody List<Integer> empIds){
        return Result.success(empService.removeByIds(empIds));
    }
    @PostMapping("/updateOrAddEmp")
    public Result updateOrAddEmp(@RequestBody Emp emp){
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("emp_name", emp.getEmpName())
                .ne("emp_id", emp.getEmpId());
        Emp one = empService.getOne(wrapper);
        if(emp.getDeptId()==null||emp.getEmpAge()==null||emp.getEmpSex()==null||emp.getEmpName()==null){
            return Result.error("lack message");
        } else if (emp.getPassword()==null) {
            emp.setPassword("123456");
        } else if (one!=null){//empName不能重复
            return Result.error("empName重复");
        }
        return Result.success(empService.saveOrUpdate(emp));
    }
    @GetMapping("/findAll")
    public Result findAllEmp(){
       return Result.success(empMapper.selectList(null));
    }
    @GetMapping("/find/{id}")
    public Result findById(@PathVariable("id") Integer empId){
        return Result.success(empMapper.selectById(empId));
    }
    @GetMapping("/find/empName/{empName}")
    public Result findByName(@PathVariable String empName){//根据empName查Emp
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("emp_name",empName);
        return Result.success(empService.getOne(wrapper));
    }
    @DeleteMapping("/deleteEmp/{id}")
    public Result deleteEmp(@PathVariable("id") Integer empId){
        return Result.success(empService.removeById(empId));
    }

//    @GetMapping("/Page")
//    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String empName,@RequestParam(value = "empAge",required = false) Integer empAge){
//        pageNum=(pageNum-1)*pageSize;
//        empName="%"+empName+"%";
//        String empAgeString;
//        if(empAge==null){
//            empAgeString="%";
//        }
//        else {
//            empAgeString="%"+empAge+"%";
//        }
//        HashMap<String, Object> map = new HashMap<>();
//        List<Emp> data = empMapper.selectPage2(pageNum, pageSize,empName,empAgeString);
//        Integer total = empMapper.selectTotal(empName,empAgeString);
//        map.put("data",data);
//        map.put("total",total);
//        return map;
//    }
    @GetMapping("/page")
    public Result getPage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam(required = false) String empName,
                              @RequestParam(required = false) Integer empAge,
                              @RequestParam(required = false) String empSex
                              ){
        String sexString = "";
        if (empSex.equals("男")) {
            sexString="1";
        } else if (empSex.equals("女")) {
            sexString = "0";
        }
        IPage<Emp> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        if(empAge != null){
            queryWrapper
                    .eq("emp_age",empAge)
                    .like("emp_name",empName)
                    .like("emp_sex",sexString);
        }
        else {
            queryWrapper
                    .like("emp_name",empName)
                    .like("emp_sex",sexString);
        }

        return Result.success(empService.page(page,queryWrapper));
    }

    @GetMapping("/export")
    public Result exportData(HttpServletResponse response) throws IOException {
        //获取所有信息
        List<Emp> list = empService.list();
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\Zqunnnnnn\\Desktop\\study\\java\\demo\\src\\main\\resources\\templates\\export"+"/用户信息.xlsx");
//        writer.addHeaderAlias("empId","员工编号");
//        writer.addHeaderAlias("empName","员工姓名");
//        writer.addHeaderAlias("empSex","性别");
//        writer.addHeaderAlias("empAge","年龄");
//        writer.addHeaderAlias("deptId","部门编号");
        //将list赋到writer中
        writer.write(list,true);
        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName +".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
        return Result.success("true");
    }
    @PostMapping("/import")
    public Result importData(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Emp> emps = reader.read(0,1,Emp.class);
        empService.saveBatch(emps);
        return Result.success("true");
    }

    @PostMapping("/login")
    public Result login(@RequestBody EmpDto empDto){
        String empName = empDto.getEmpName();
        String password = empDto.getPassword();
        if(StrUtil.isBlank(empName)||StrUtil.isBlank(password)){
            return Result.error("用户名或密码为空");
        }
        return Result.success(empService.login(empDto));
    }

    @PostMapping("/register")
    public Result register(@RequestBody EmpDto empDto){
        String empName = empDto.getEmpName();
        String password = empDto.getPassword();
        if(StrUtil.isBlank(empName)||StrUtil.isBlank(password)){
            return Result.error("用户名或密码为空");
        }
        return Result.success(empService.register(empDto));
    }
}
