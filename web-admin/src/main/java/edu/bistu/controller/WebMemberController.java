package edu.bistu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.bistu.annotation.SystemLog;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebLog;
import edu.bistu.domain.entity.WebManager;
import edu.bistu.domain.entity.WebMember;
import edu.bistu.service.WebManagerService;
import edu.bistu.service.WebMemberService;
import edu.bistu.utils.MinioUtils;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 成员管理(WebMember)表控制层
 *
 * @author KarigenBrown
 * @since 2024-04-03 14:12:15
 */
@RateLimiter(name = "default")
@RestController
@RequestMapping("/webMember")
public class WebMemberController {
    /**
     * 服务对象
     */
    @Autowired
    private WebMemberService webMemberService;

    @Autowired
    private MinioUtils minioUtils;

    @Autowired
    private WebManagerService webManagerService;

    @PreAuthorize("hasAuthority('个人信息管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @GetMapping("/{userNumber}")
    public Response<WebMember> getMemberByNumber(@PathVariable("userNumber") String number) {
        return Response.ok(
                webMemberService.lambdaQuery()
                        .eq(WebMember::getNumber, number)
                        .one()
        );
    }

    @SystemLog(businessName = "修改个人头像")
    @PreAuthorize("hasAuthority('个人信息管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @PostMapping("/photo")
    public Response<Map<String, String>> uploadPhoto(@RequestParam("photoName") String photoName,
                                                     @RequestPart("photo") MultipartFile file) {
        String url = minioUtils.upload("web", "memberPhoto/" + photoName, file);
        int index = url.lastIndexOf('?');
        url = url.substring(0, index);
        return Response.ok(Map.of("photoUrl", url));
    }

    @SystemLog(businessName = "修改个人信息")
    @PreAuthorize("hasAuthority('个人信息管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @PutMapping
    public Response<Object> putMemberById(@RequestBody WebMember member) {
        webMemberService.updateById(member);
        return Response.ok();
    }

    //--------------------------------------------------

    @PreAuthorize("hasAuthority('成员管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @GetMapping("/all/{pageSize}/{currentPage}")
    public Response<Map<String, Object>> getAllMembers(@PathVariable("pageSize") Integer pageSize,
                                                       @PathVariable("currentPage") Integer currentPage) {
        Page<WebMember> page = webMemberService.page(new Page<>(currentPage, pageSize));
        return Response.ok(Map.of(
                "rows", page.getRecords(),
                "total", page.getTotal()
        ));
    }

    @PreAuthorize("hasAuthority('成员管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @GetMapping("/name/{name}")
    public Response<List<WebMember>> getMembersByName(@PathVariable("name") String name) {
        return Response.ok(
                webMemberService.lambdaQuery()
                        .like(WebMember::getName, name)
                        .list()
        );
    }

    @PreAuthorize("hasAuthority('成员管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @GetMapping("/identity/{identity}/{pageSize}/{currentPage}")
    public Response<Map<String, Object>> getMembersByIdentity(@PathVariable("pageSize") Integer pageSize,
                                                              @PathVariable("currentPage") Integer currentPage,
                                                              @PathVariable("identity") String identity) {
        Page<WebMember> page = webMemberService.lambdaQuery()
                .eq(WebMember::getIdentity, identity)
                .page(new Page<>(currentPage, pageSize));
        return Response.ok(Map.of(
                "rows", page.getRecords(),
                "total", page.getTotal()
        ));
    }

    @SystemLog(businessName = "删除成员")
    @PreAuthorize("hasAuthority('成员管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @DeleteMapping("/{id}")
    @Transactional
    public Response<Object> deleteMemberById(@PathVariable("id") Integer id) {
        String number = webMemberService.getById(id).getNumber();
        webMemberService.removeById(id);
        webManagerService.lambdaUpdate()
                .eq(WebManager::getNumber, number)
                .remove();
        return Response.ok();
    }

    @SystemLog(businessName = "新增成员")
    @PreAuthorize("hasAuthority('成员管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
    @PostMapping
    public Response<Object> postMember(@RequestBody WebMember member) {
        member.setPhotoUrl("");
        webMemberService.save(member);
        return Response.ok(Map.of("id", member.getId()));
    }
}

