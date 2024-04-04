package edu.bistu.controller;


import edu.bistu.config.MinioConfig;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebMember;
import edu.bistu.service.WebMemberService;
import edu.bistu.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{userNumber}")
    public Response<WebMember> getMemberByNumber(@PathVariable("userNumber") String number) {
        return Response.ok(webMemberService.lambdaQuery().eq(WebMember::getNumber, number).one());
    }

    @PostMapping("/photo")
    public Response<Map<String, String>> uploadPhoto(@RequestParam("photoName") String photoName,
                                                     @RequestPart("photo") MultipartFile file) {
        String url = minioUtils.upload("web", "memberPhoto/" + photoName, file);
        int index = url.lastIndexOf('?');
        url = url.substring(0, index);
        return Response.ok(Map.of("photoUrl", url));
    }

    @PutMapping
    public Response<Object> putMemberById(@RequestBody WebMember member) {
        webMemberService.updateById(member);
        return Response.ok();
    }

    //

    @GetMapping("/all")
    public Response<List<WebMember>> getAllMembers() {
        return Response.ok(webMemberService.list());
    }

    @GetMapping("/name/{name}")
    public Response<List<WebMember>> getMembersByName(@PathVariable("name") String name) {
        return Response.ok(webMemberService.lambdaQuery().like(WebMember::getName, name).list());
    }

    @GetMapping("/identity/{identity}")
    public Response<List<WebMember>> getMembersByIdentity(@PathVariable("identity") String identity) {
        return Response.ok(webMemberService.lambdaQuery().eq(WebMember::getIdentity, identity).list());
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteMemberById(@PathVariable("id") Integer id) {
        webMemberService.removeById(id);
        return Response.ok();
    }

    @PostMapping
    public Response<Object> postMember(@RequestBody WebMember member) {
        member.setPhotoUrl("");
        webMemberService.save(member);
        Integer id = webMemberService.lambdaQuery().orderByDesc(WebMember::getId).list().get(0).getId();
        return Response.ok(Map.of("id", id));
    }
}

