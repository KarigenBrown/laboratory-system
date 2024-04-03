package edu.bistu.controller;


import edu.bistu.config.MinioConfig;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebMember;
import edu.bistu.service.WebMemberService;
import edu.bistu.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public Response<Map<String, String>> uploadPhoto(@RequestHeader("number") String number, @RequestPart("photo") MultipartFile file) {
        int index = file.getOriginalFilename().lastIndexOf('.');
        String suffix = file.getOriginalFilename().substring(index);
        String url = minioUtils.upload("web", "memberPhoto/" + number + suffix, file);
        index = url.lastIndexOf('?');
        url = url.substring(0, index);
        return Response.ok(Map.of("photoUrl", url));
    }

    @PutMapping
    public Response<Object> putMemberById(@RequestBody WebMember member) {
        webMemberService.updateById(member);
        return Response.ok();
    }
}

