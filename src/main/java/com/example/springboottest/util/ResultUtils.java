package com.example.springboottest.util;

import com.example.springboottest.exception.ErrorCode;
import com.example.springboottest.response.CommonResponse;

public class ResultUtils {

    public static <T> CommonResponse<T> success(T data){
        return new CommonResponse<>(0, "ok", data);
    }

    public static <T> CommonResponse<T> error(ErrorCode code){
        return new CommonResponse<>(code.getCode(), code.getMessage(), null);
    }

    public static <T> CommonResponse<T> error(int code, String message){
        return new CommonResponse<>(code, message, null);
    }

    public static <T> CommonResponse<T> success(){
        return new CommonResponse<>(0, "ok", null);
    }

}
