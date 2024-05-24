package edu.bistu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebLog;
import edu.bistu.service.WebLogService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * (WebLog)表控制层
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:57:05
 */
@RateLimiter(name = "default")
@RestController
@RequestMapping("/webLog")
public class WebLogController {
    /**
     * 服务对象
     */
    @Autowired
    private WebLogService webLogService;

    @GetMapping("/all/{pageSize}/{currentPage}")
    Response<Map<String, Object>> getAllLogs(@PathVariable("pageSize") Integer pageSize,
                                             @PathVariable("currentPage") Integer currentPage) {
        Page<WebLog> page = webLogService.lambdaQuery()
                .orderByDesc(WebLog::getCreateTime)
                .page(new Page<>(currentPage, pageSize));
        return Response.ok(Map.of(
                "rows", page.getRecords(),
                "total", page.getTotal()
        ));
    }
}

