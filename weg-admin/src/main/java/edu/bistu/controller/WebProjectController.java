package edu.bistu.controller;


import edu.bistu.annotation.SystemLog;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebActivity;
import edu.bistu.domain.entity.WebProject;
import edu.bistu.service.WebProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * (WebProject)表控制层
 *
 * @author makejava
 * @since 2024-04-07 14:18:44
 */
@PreAuthorize("hasAuthority('成果管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
@RestController
@RequestMapping("/webProject")
public class WebProjectController {
    /**
     * 服务对象
     */
    @Autowired
    private WebProjectService webProjectService;

    @GetMapping("/all")
    public Response<List<WebProject>> getAllProjects() {
        return Response.ok(webProjectService.list());
    }

    @SystemLog(businessName = "删除项目")
    @DeleteMapping("/{id}")
    public Response<Object> deleteProjectById(@PathVariable("id") Integer id) {
        webProjectService.removeById(id);
        return Response.ok();
    }

    @SystemLog(businessName = "新增项目")
    @PostMapping
    public Response<Map<String, Integer>> postProject(@RequestBody WebProject project) {
        webProjectService.save(project);
        return Response.ok(Map.of("id", project.getId()));
    }

    @SystemLog(businessName = "更新项目")
    @PutMapping
    public Response<Object> putActivity(@RequestBody WebProject project) {
        webProjectService.updateById(project);
        return Response.ok();
    }

    @GetMapping("/{title}")
    public Response<List<WebProject>> getProjectsByTitle(@PathVariable("title") String title) {
        return Response.ok(
                webProjectService.lambdaQuery()
                        .like(WebProject::getName, title)
                        .list()
        );
    }
}

