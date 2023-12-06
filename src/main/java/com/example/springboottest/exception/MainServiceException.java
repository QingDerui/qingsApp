package com.example.springboottest.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainServiceException extends RuntimeException{

    private Integer code;
    private String message;

    public MainServiceException(Integer code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
