package com.example.springboottest.exception;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {

    NO_AUTH(999,"暂无权限访问"),
    SYSTEM_ERROR(50000,"系统内部异常");
    //返回码
    private final int code;
    //操作响应信息
    private final String message;

    //get方法
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static class LogInException extends MainServiceException{
        public LogInException(){
            super(NO_AUTH.getCode(), NO_AUTH.getMessage());
        }
    }

    public static class SystemErrorException extends MainServiceException{
        public SystemErrorException(){
            super(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getMessage());
        }
    }
}
