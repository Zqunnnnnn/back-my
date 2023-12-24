package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private String message;
    private Object data;

    public static Result success(){
        return new Result("200","Success",null);
    }
    public static Result success(Object data){
        return new Result("200","Success",data);
    }
    public static Result success(String message){
        return new Result("200",message,null);
    }
    public static Result error(String message){
        return new Result("400",message,null);
    }
    public static Result error(String code,String message){
        return new Result(code,message,null);
    }
}
