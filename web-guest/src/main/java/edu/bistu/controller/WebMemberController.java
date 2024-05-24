package edu.bistu.controller;


import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebMember;
import edu.bistu.service.WebMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 成员管理(WebMember)表控制层
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:57:26
 */
@Tag(name = "成员控制器")
@RestController
@RequestMapping("/webMember")
public class WebMemberController {
    /**
     * 服务对象
     */
    @Autowired
    private WebMemberService webMemberService;

    @Operation(summary = "查询所有成员的信息")
    @GetMapping("/all")
    public Response<List<WebMember>> getAllMembers() {
        return Response.ok(webMemberService.list());
    }

    @Operation(summary = "根据学工号(唯一标识)查看成员信息")
    @Parameter(name = "number", description = "学工号")
    @GetMapping("/{number}")
    public Response<WebMember> getByNumber(@PathVariable("number") String number) {
        return Response.ok(webMemberService.lambdaQuery().eq(WebMember::getNumber, number).one());
    }

    @Operation(summary = "根据学工号查询成员的照片链接")
    @Parameter(name = "number", description = "学工号")
    @GetMapping("/{number}/photoUrl")
    public Response<String> getPhotoUrlByNumber(@PathVariable("number") String number) {
        return Response.ok(webMemberService.lambdaQuery().eq(WebMember::getNumber, number).one().getPhotoUrl());
    }

    @Operation(summary = "根据身份查询成员")
    @Parameter(name = "identity", description = "成员身份,包括教授、副教授、在学生、毕业生")
    @GetMapping("/{identity}")
    public Response<List<WebMember>> getByIdentity(@PathVariable("identity") String identity) {
        return Response.ok(webMemberService.lambdaQuery().like(WebMember::getIdentity, identity).list());
    }
}

