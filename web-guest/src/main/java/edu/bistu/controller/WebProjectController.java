package edu.bistu.controller;


import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebAchievement;
import edu.bistu.domain.entity.WebProject;
import edu.bistu.service.WebProjectService;
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
 * (WebProject)表控制层
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:57:34
 */
@Tag(name = "项目控制器")
@RestController
@RequestMapping("/webProject")
public class WebProjectController {
    /**
     * 服务对象
     */
    @Autowired
    private WebProjectService webProjectService;

    @Operation(summary = "根据项目的状态查询项目")
    @Parameter(name = "status", description = "项目状态,包括在研、结题")
    @GetMapping("/{status}")
    public Response<List<WebProject>> getProjectByStatus(@PathVariable("status") String status) {
        return Response.ok(webProjectService.lambdaQuery().eq(WebProject::getStatus, status).list());
    }
}

