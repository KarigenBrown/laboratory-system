package edu.bistu.controller;


import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebDemo;
import edu.bistu.service.WebDemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (WebDemo)表控制层
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:56:58
 */
@Tag(name = "demo控制器")
@RestController
@RequestMapping("/webDemo")
public class WebDemoController {
    /**
     * 服务对象
     */
    @Autowired
    private WebDemoService webDemoService;

    @Operation(summary = "查询所有demo")
    @GetMapping("/all")
    public Response<List<WebDemo>> getAllDemos() {
        return Response.ok(webDemoService.list());
    }
}

