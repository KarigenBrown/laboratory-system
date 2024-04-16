package edu.bistu.handler.exception;

import edu.bistu.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Response<Object> exceptionHandler(Exception exception) {
        // 打印异常信息
        log.error(exception.getMessage());
        // 从异常对象中获取提示信息封装返回
        return Response.error(500, exception.getMessage());
    }
}
