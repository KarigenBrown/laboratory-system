package edu.bistu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.bistu.annotation.SystemLog;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebManager;
import edu.bistu.domain.entity.WebMember;
import edu.bistu.domain.entity.WebRawMember;
import edu.bistu.enums.HttpCodeEnum;
import edu.bistu.exeception.SystemException;
import edu.bistu.service.WebManagerService;
import edu.bistu.service.WebMemberService;
import edu.bistu.service.WebRawMemberService;
import edu.bistu.utils.JwtUtils;
import edu.bistu.utils.RedisCache;
import edu.bistu.utils.SecurityUtils;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * (WebManager)表控制层
 *
 * @author makejava
 * @since 2024-04-03 13:24:43
 */
@RateLimiter(name = "default")
@RestController
@RequestMapping("/webManager")
public class WebManagerController {
    /**
     * 服务对象
     */
    @Autowired
    private WebManagerService webManagerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    private WebRawMemberService webRawMemberService;

    @Lazy
    @Autowired
    private WebMemberService webMemberService;

    @Autowired
    private RedisCache redisCache;

    @PreAuthorize("hasAuthority('权限管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @GetMapping("/all/{pageSize}/{currentPage}")
    public Response<Map<String, Object>> getAllWebManager(@PathVariable("pageSize") Integer pageSize,
                                                          @PathVariable("currentPage") Integer currentPage) {
        Page<WebManager> page = webManagerService.page(new Page<>(currentPage, pageSize));
        List<WebManager> managers = page.getRecords();
        managers.forEach(manager -> manager.setIdentity(webMemberService.lambdaQuery().eq(WebMember::getNumber, manager.getNumber())
                .one()
                .getIdentity()));
        return Response.ok(Map.of(
                "rows", managers,
                "total", page.getTotal()
        ));
    }

    @PreAuthorize("hasAuthority('权限管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @SystemLog(businessName = "删除成员")
    @DeleteMapping("/{id}")
    @Transactional
    public Response<Object> deleteManagerById(@PathVariable("id") Integer id) {
        webManagerService.removeById(id);
        return Response.ok();
    }

    @PreAuthorize("hasAuthority('权限管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @GetMapping("/{username}")
    public Response<List<WebManager>> getManagerByUsername(@PathVariable("username") String username) {
        return Response.ok(
                webManagerService.lambdaQuery()
                        .like(WebManager::getUsername, username)
                        .list()
        );
    }

    @PreAuthorize("hasAuthority('权限管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @SystemLog(businessName = "修改一个成员")
    @PutMapping
    public Response<Object> putManagerById(@RequestBody WebManager manager) {
        if (manager.getUsername().equals(manager.getPassword())) {
            manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        }
        webManagerService.updateById(manager);
        return Response.ok();
    }

    // ----------------------------------------------------------------

    @SystemLog(businessName = "登录")
    @PostMapping("/login")
    public Response<Map<String, String>> login(@RequestBody WebManager manager) {
        return Response.ok(webManagerService.login(manager));
    }

    @Transactional
    @PostMapping("/register")
    public Response<Object> register(@RequestBody Map<String, String> user) {
        WebRawMember webRawMember = webRawMemberService.lambdaQuery()
                .eq(WebRawMember::getNumber, user.get("number"))
                .eq(WebRawMember::getIdentity, user.get("identity"))
                .one();
        if (Objects.isNull(webRawMember)) {
            throw new SystemException(HttpCodeEnum.USER_NOT_EXIST);
        }

        WebManager webManager = new WebManager()
                .setUsername(user.get("username"))
                .setPassword(passwordEncoder.encode(user.get("password")))
                .setNumber(webRawMember.getNumber());
        webManagerService.save(webManager);

        WebMember webMember = new WebMember()
                .setName(user.get("username"))
                .setIdentity(webRawMember.getIdentity())
                .setNumber(webRawMember.getNumber());
        webMemberService.save(webMember);

        return Response.ok();
    }

    @SystemLog(businessName = "退出")
    @DeleteMapping("/logout")
    public Response<Object> logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userid = JwtUtils.parseJWT(token).getSubject();
        // request.getServletContext().removeAttribute(userid);
        redisCache.deleteCacheObject(userid);
        return Response.ok();
    }

    @SystemLog(businessName = "修改密码")
    @PutMapping("/changePassword")
    public Response<Object> changePassword(@RequestParam("newPassword") String newPassword) {
        Integer userId = SecurityUtils.getUserId();
        webManagerService.lambdaUpdate()
                .eq(WebManager::getId, userId)
                .set(WebManager::getPassword, passwordEncoder.encode(newPassword))
                .update();
        return Response.ok();
    }
}

