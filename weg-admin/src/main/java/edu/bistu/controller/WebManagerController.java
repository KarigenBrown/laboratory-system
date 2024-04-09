package edu.bistu.controller;


import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebManager;
import edu.bistu.service.WebManagerService;
import edu.bistu.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/all")
    public Response<List<WebManager>> getAllWebManager() {
        return Response.ok(webManagerService.list());
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteManagerById(@PathVariable("id") Integer id) {
        webManagerService.removeById(id);
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

    @PutMapping
    public Response<Object> putManagerById(@RequestBody WebManager manager) {
        webManagerService.updateById(manager);
        return Response.ok();
    }

    // ----------------------------------------------------------------

    @PostMapping("/login")
    public Response<Map<String, String>> login(@RequestBody WebManager manager) {
        return Response.ok(webManagerService.login(manager));
    }

    @GetMapping("/logout")
    public Response<Object> logout(HttpServletRequest request){
        String token = request.getHeader("token");
        String userid = JwtUtils.parseJWT(token).getSubject();
        request.getSession().removeAttribute(userid);
        return Response.ok();
    }
}

