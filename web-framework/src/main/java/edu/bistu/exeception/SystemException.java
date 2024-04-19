package edu.bistu.exeception;

import edu.bistu.enums.HttpCodeEnum;
import lombok.Getter;

@Getter
public class SystemException extends RuntimeException {
    private int code;
    private String message;

    public SystemException(HttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMessage());
        this.code = httpCodeEnum.getCode();
        this.message = httpCodeEnum.getMessage();
    }
}
