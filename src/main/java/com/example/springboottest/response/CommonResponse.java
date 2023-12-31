package com.example.springboottest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {

    private int code;

    private String message;

    private T data;

    public CommonResponse(int code, T data){
        this.code = code;
        this.data = data;
    }

    public static <T> CommonResponse success(T data){
        return new CommonResponse<>(0, data);
    }
}
