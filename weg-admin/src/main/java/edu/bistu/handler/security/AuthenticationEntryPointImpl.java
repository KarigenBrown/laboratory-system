package edu.bistu.handler.security;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bistu.domain.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
        Response<Object> myResponse = Response.error(505,"登录错误");

        response.setStatus(200);
        response.setContentType(ContentType.JSON.toString());
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        try {
            response.getWriter().print(objectMapper.writeValueAsString(myResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
