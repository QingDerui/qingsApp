package com.example.springboottest.interceptor;

import com.example.springboottest.exception.MainServiceException;
import com.example.springboottest.response.CommonResponse;
import com.example.springboottest.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MainServiceException.class)
    public CommonResponse mainServiceExceptionHandler(MainServiceException e){
        log.warn("Exception" + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }
}
