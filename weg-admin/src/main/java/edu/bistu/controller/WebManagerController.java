package edu.bistu.controller;


import edu.bistu.annotation.SystemLog;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebManager;
import edu.bistu.domain.entity.WebMember;
import edu.bistu.domain.entity.WebRawMember;
import edu.bistu.service.WebManagerService;
import edu.bistu.service.WebMemberService;
import edu.bistu.service.WebRawMemberService;
import edu.bistu.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @GetMapping("/all")
    public Response<List<WebManager>> getAllWebManager() {
        return Response.ok(webManagerService.list());
    }

    @SystemLog(businessName = "删除成员")
    @DeleteMapping("/{id}")
    @Transactional
    public Response<Object> deleteManagerById(@PathVariable("id") Integer id) {
        String number = webManagerService.getById(id).getNumber();
        webManagerService.removeById(id);
        webMemberService.lambdaUpdate()
                .eq(WebMember::getNumber, number)
                .remove();
        return Response.ok();
    }

    @GetMapping("/{username}")
    public Response<List<WebManager>> getManagerByUsername(@PathVariable("username") String username) {
        return Response.ok(
                webManagerService.lambdaQuery()
                        .like(WebManager::getUsername, username)
                        .list()
        );
    }

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
            return Response.error(404, "用户未找到");
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
        request.getServletContext().removeAttribute(userid);
        return Response.ok();
    }
}

