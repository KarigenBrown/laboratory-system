package edu.bistu.handler.security;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bistu.domain.Response;
import edu.bistu.enums.HttpCodeEnum;
import edu.bistu.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        Response<Object> result = switch (authException) {
            case BadCredentialsException ignored1 ->
                    Response.error(HttpCodeEnum.LOGIN_ERROR.getCode(), authException.getMessage());
            case InsufficientAuthenticationException ignored2 -> Response.error(HttpCodeEnum.NEED_LOGIN);
            default -> Response.error(HttpCodeEnum.SYSTEM_ERROR.getCode(), "认证或授权失败");
        };
        // 响应给前端
        WebUtils.renderString(response, objectMapper.writeValueAsString(result));
    }
}
