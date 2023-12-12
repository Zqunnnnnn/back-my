package com.example.demo.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.Emp;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.service.impl.EmpServiceImpl;
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
    public boolean deleteEmps(@RequestBody List<Integer> empIds){
        return empService.removeByIds(empIds);
    }
    @PostMapping("/updateOrAddEmp")
    public boolean updateOrAddEmp(@RequestBody Emp emp){
        if(emp.getDeptId()==null||emp.getEmpAge()==null||emp.getEmpSex()==null||emp.getEmpName()==null){
            return false;
        }
        return empService.saveOrUpdate(emp);
    }
    @GetMapping("/findAll")
    public List<Emp> findAllEmp(){
       return empMapper.selectList(null);
    }
    @GetMapping("/find/{id}")
    public Emp findById(@PathVariable("id") Integer empId){
        return empMapper.selectById(empId);
    }
    @DeleteMapping("/deleteEmp/{id}")
    public boolean deleteEmp(@PathVariable("id") Integer empId){
        return empService.removeById(empId);
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
    public IPage<Emp> getPage(@RequestParam Integer pageNum,
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

        return empService.page(page,queryWrapper);
    }

    @GetMapping("/export")
    public void exportData(HttpServletResponse response) throws IOException {
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
    }
    @PostMapping("/import")
    public void importData(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Emp> emps = reader.readAll(Emp.class);
        empService.saveBatch(emps);
    }



}
