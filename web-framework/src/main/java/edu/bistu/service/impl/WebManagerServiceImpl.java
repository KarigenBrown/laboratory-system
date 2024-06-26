package edu.bistu.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.bistu.domain.entity.WebMember;
import edu.bistu.enums.HttpCodeEnum;
import edu.bistu.exeception.SystemException;
import edu.bistu.mapper.WebManagerMapper;
import edu.bistu.domain.entity.WebManager;
import edu.bistu.service.WebManagerService;
import edu.bistu.service.WebMemberService;
import edu.bistu.utils.JwtUtils;
import edu.bistu.utils.RedisCache;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * (WebManager)表服务实现类
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:49:20
 */
@Service("webManagerService")
public class WebManagerServiceImpl extends ServiceImpl<WebManagerMapper, WebManager> implements WebManagerService, UserDetailsService {

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ServletContext context;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private WebMemberService webMemberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        WebManager manager = baseMapper.selectOne(
                Wrappers.<WebManager>lambdaQuery()
                        .eq(WebManager::getUsername, username)
        );
        // 如果没有查询到用户
        if (Objects.isNull(manager)) {
            throw new SystemException(HttpCodeEnum.LOGIN_ERROR);
        }

        // 查询对应的身份信息
        String identity = webMemberService.lambdaQuery()
                .eq(WebMember::getNumber, manager.getNumber())
                .one()
                .getIdentity();
        manager.setIdentity(identity);

        // 把用户封装成UserDetails返回
        return manager;
    }

    @Override
    public Map<String, String> login(WebManager manager) {
        // AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(manager.getUsername(), manager.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 如果认证没通过,给出对应的提示
        if (Objects.isNull(authentication)) {
            throw new SystemException(HttpCodeEnum.LOGIN_ERROR);
        }

        // 如果认证通过了,使用userid生成一个jwt,返回jwt
        WebManager loginManager = (WebManager) authentication.getPrincipal();
        String userid = loginManager.getId().toString();
        String jwt = JwtUtils.createJWT(userid);

        // 把完整的用户信息存入servlet context,userid作为key
        // context.setAttribute(userid, loginManager);
        // 存入redis
        redisCache.setCacheObject(userid, loginManager);

        WebMember member = webMemberService.lambdaQuery()
                .eq(WebMember::getNumber, loginManager.getNumber())
                .one();
        return Map.of(
                "token", jwt,
                "number", loginManager.getNumber(),
                "identity", member.getIdentity(),
                "permits", loginManager.getPermits()
        );
    }
}

