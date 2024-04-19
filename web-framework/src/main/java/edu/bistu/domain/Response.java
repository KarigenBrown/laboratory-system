package edu.bistu.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.bistu.enums.HttpCodeEnum;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static <T> Response<T> ok(T data) {
        return new Response<T>()
                .setCode(HttpCodeEnum.SUCCESS.getCode())
                .setMessage(HttpCodeEnum.SUCCESS.getMessage())
                .setData(data);
    }

    public static Response<Object> ok() {
        return Response.ok(null);
    }

    public static <T> Response<T> error(int code, String message) {
        return new Response<T>()
                .setCode(code)
                .setMessage(message);
    }

    public static Response<Object> error(HttpCodeEnum httpCodeEnum) {
        return new Response<>()
                .setCode(httpCodeEnum.getCode())
                .setMessage(httpCodeEnum.getMessage());
    }
}
