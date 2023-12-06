package com.example.springboottest.interceptor;

import com.example.springboottest.exception.MainServiceException;
import com.example.springboottest.response.CommonResponse;
import com.example.springboottest.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static com.example.springboottest.exception.ErrorCode.SYSTEM_ERROR;

@Aspect
@Component
@Slf4j
public class ResultHandler {

    @Around(value = "@within(org.springframework.web.bind.annotation.RestController)")
    public Object afterControllerBoxing(ProceedingJoinPoint proceedingJoinPoint) throws MainServiceException {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
            if (result instanceof CommonResponse) {
                return result;
            }

            result = ResultUtils.success(result);
            return result;
        } catch (MainServiceException e) {
            if (e.getCode() != null) {
                result = ResultUtils.error(e.getCode(), e.getMessage());
            } else {
                result = ResultUtils.error(SYSTEM_ERROR.getCode(), e.getMessage());
            }
            return result;
        } catch (Throwable throwable) {
            throw new MainServiceException(SYSTEM_ERROR.getCode(), throwable.getMessage());
        }
    }
}
