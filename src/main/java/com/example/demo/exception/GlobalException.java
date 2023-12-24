package com.example.demo.exception;

import com.example.demo.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalException {
    /**
     * 如果抛出ServiceException就调用该方法
     */
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Result handle(LoginException se){
        return Result.error(se.getCode(),se.getMessage());
    }
}
