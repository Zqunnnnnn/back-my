package com.example.demo;

import com.example.demo.bean.Student;
import com.example.demo.bean.User;
import com.example.demo.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private StudentServiceImpl studentService;

    @Test
    void contextLoads() {
        long milliseconds = 1636978900000L; // 表示2021年11月16日 10:35:00的毫秒数
        Date date = new Date(milliseconds);
        Student student = new Student(151, "jack31", 18, "SuZhou", date);
        studentService.addStudent(student);
        System.out.println("ok----------------");

    }

}
