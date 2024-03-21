package edu.bistu.controller;


import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebActivity;
import edu.bistu.service.WebActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (WebActivity)表控制层
 *
 * @author makejava
 * @since 2024-03-20 19:56:51
 */
@Tag(name = "活动控制器")
@RestController
@RequestMapping("/webActivity")
public class WebActivityController {
    /**
     * 服务对象
     */
    @Autowired
    private WebActivityService webActivityService;

    @Operation(summary = "查询所有活动")
    @GetMapping("/all")
    public Response<List<WebActivity>> getAllActivities() {
        return Response.ok(webActivityService.list());
    }

    @Operation(summary = "根据id查询所有活动的信息")
    @GetMapping("/{id}")
    public Response<WebActivity> getActivityById(@PathVariable("id") Integer id) {
        return Response.ok(webActivityService.getById(id));
    }
}

