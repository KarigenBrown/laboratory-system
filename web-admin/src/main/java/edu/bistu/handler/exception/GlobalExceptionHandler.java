package edu.bistu.handler.exception;

import edu.bistu.domain.Response;
import edu.bistu.enums.HttpCodeEnum;
import edu.bistu.exeception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public Response<Object> systemExceptionHandler(SystemException systemException) {
        // 打印异常信息
        log.error(systemException.getMessage());
        systemException.printStackTrace();
        // 从异常对象中获取提示信息封装返回
        return Response.error(systemException.getCode(), systemException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response<Object> exceptionHandler(Exception exception) {
        // 打印异常信息
        log.error(exception.getMessage());
        exception.printStackTrace();
        // 从异常对象中获取提示信息封装返回
        return Response.error(HttpCodeEnum.SYSTEM_ERROR.getCode(), exception.getMessage());
    }
}
