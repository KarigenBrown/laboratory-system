package edu.bistu.filter;

import edu.bistu.domain.entity.WebManager;
import edu.bistu.enums.HttpCodeEnum;
import edu.bistu.exeception.SystemException;
import edu.bistu.utils.JwtUtils;
import edu.bistu.utils.RedisCache;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private ServletContext context;

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 还未登录，放行
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String userid = JwtUtils.parseJWT(token).getSubject();
        // 从servlent context中获取用户信息
        // WebManager loginManager = (WebManager) context.getAttribute(userid);
        // 从redis中获取
        WebManager loginManager = redisCache.getCacheObject(userid);
        if (Objects.isNull(loginManager)) {
            throw new SystemException(HttpCodeEnum.NEED_LOGIN);
        }
        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginManager, null, loginManager.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
