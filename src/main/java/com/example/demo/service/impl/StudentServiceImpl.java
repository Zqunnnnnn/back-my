package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.Student;
import com.example.demo.service.StudentService;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Zqunnnnnn
* @description 针对表【student】的数据库操作Service实现
* @createDate 2023-11-15 23:12:12
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{
    @Autowired
    private StudentMapper studentMapper;
    public int addStudent(Student student){
        return studentMapper.insert(student);
    }

    public int deleteStudentById(int id){
        return studentMapper.deleteById(id);
    }

    public int updateOrSaveStudent(Student student){
        return studentMapper.updateById(student);
    }

    public List<Student> findAllStudent(){
        return studentMapper.selectList(null);
    }
}




