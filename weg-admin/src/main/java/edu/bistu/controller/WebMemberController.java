package edu.bistu.controller;


import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebMember;
import edu.bistu.service.WebMemberService;
import edu.bistu.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 成员管理(WebMember)表控制层
 *
 * @author makejava
 * @since 2024-04-03 14:12:15
 */
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

    @PreAuthorize("hasAuthority('个人')")
    @GetMapping("/{userNumber}")
    public Response<WebMember> getMemberByNumber(@PathVariable("userNumber") String number) {
        return Response.ok(
                webMemberService.lambdaQuery()
                        .eq(WebMember::getNumber, number)
                        .one()
        );
    }

    @PreAuthorize("hasAuthority('个人')")
    @PostMapping("/photo")
    public Response<Map<String, String>> uploadPhoto(@RequestParam("photoName") String photoName,
                                                     @RequestPart("photo") MultipartFile file) {
        String url = minioUtils.upload("web", "memberPhoto/" + photoName, file);
        int index = url.lastIndexOf('?');
        url = url.substring(0, index);
        return Response.ok(Map.of("photoUrl", url));
    }

    @PreAuthorize("hasAuthority('个人')")
    @PutMapping
    public Response<Object> putMemberById(@RequestBody WebMember member) {
        webMemberService.updateById(member);
        return Response.ok();
    }

    //--------------------------------------------------

    @PreAuthorize("hasAuthority('人员管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授')")
    @GetMapping("/all")
    public Response<List<WebMember>> getAllMembers() {
        return Response.ok(webMemberService.list());
    }

    @PreAuthorize("hasAuthority('人员管理')")
    @GetMapping("/name/{name}")
    public Response<List<WebMember>> getMembersByName(@PathVariable("name") String name) {
        return Response.ok(
                webMemberService.lambdaQuery()
                        .like(WebMember::getName, name)
                        .list()
        );
    }

    @PreAuthorize("hasAuthority('人员管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授')")
    @GetMapping("/identity/{identity}")
    public Response<List<WebMember>> getMembersByIdentity(@PathVariable("identity") String identity) {
        return Response.ok(
                webMemberService.lambdaQuery()
                        .eq(WebMember::getIdentity, identity)
                        .list()
        );
    }

    @PreAuthorize("hasAuthority('人员管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授')")
    @DeleteMapping("/{id}")
    public Response<Object> deleteMemberById(@PathVariable("id") Integer id) {
        webMemberService.removeById(id);
        return Response.ok();
    }

    @PreAuthorize("hasAuthority('人员管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授')")
    @PostMapping
    public Response<Object> postMember(@RequestBody WebMember member) {
        member.setPhotoUrl("");
        webMemberService.save(member);
        return Response.ok(Map.of("id", member.getId()));
    }
}

