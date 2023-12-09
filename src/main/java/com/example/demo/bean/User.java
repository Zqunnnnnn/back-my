package com.example.demo.bean;


import lombok.Data;

import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component

public class User {
    private Integer id;
    private String name;
    private Integer age;
    public void say(User user) {
        System.out.println(user.getName()+"今年"+user.getAge()+"岁了");
    }


}
