package edu.bistu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bistu.annotation.SystemLog;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebActivity;
import edu.bistu.domain.entity.WebDemo;
import edu.bistu.service.WebDemoService;
import edu.bistu.utils.MinioUtils;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (WebDemo)表控制层
 *
 * @author makejava
 * @since 2024-04-06 16:28:42
 */
@PreAuthorize("hasAuthority('Demo管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
@RateLimiter(name = "default")
@RestController
@RequestMapping("/webDemo")
public class WebDemoController {
    /**
     * 服务对象
     */
    @Autowired
    private WebDemoService webDemoService;

    @Autowired
    private MinioUtils minioUtils;

    @GetMapping("/all/{pageSize}/{currentPage}")
    public Response<Map<String, Object>> getAllDemos(@PathVariable("pageSize") Integer pageSize,
                                                     @PathVariable("currentPage") Integer currentPage) {
        Page<WebDemo> page = webDemoService.page(new Page<>(currentPage, pageSize));
        return Response.ok(Map.of(
                "rows", page.getRecords(),
                "total", page.getTotal()
        ));
    }

    @GetMapping("/{title}")
    public Response<List<WebDemo>> getDemosByTitle(@PathVariable("title") String title) {
        return Response.ok(
                webDemoService.lambdaQuery()
                        .like(WebDemo::getTitle, title)
                        .list()
        );
    }

    @SystemLog(businessName = "删除一个Demo")
    @DeleteMapping("/{id}")
    public Response<Object> deleteActivityById(@PathVariable("id") Integer id) {
        webDemoService.removeById(id);
        return Response.ok();
    }

    @SystemLog(businessName = "新增照片")
    @SneakyThrows
    @PostMapping("/photo/upload")
    public Response<Map<String, String>> uploadPhoto(@RequestParam("title") String title,
                                                     @RequestParam("photoName") String name,
                                                     @RequestPart("photos") List<MultipartFile> files) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> photoName = objectMapper.readValue(name, Map.class);
        List<String> photoUrls = new ArrayList<>();
        String path = "demo/" + title + "/photo/";
        files.forEach(file -> {
            String url = minioUtils.upload("web", path + photoName.get(file.getOriginalFilename()), file);
            int index = url.lastIndexOf('?');
            url = url.substring(0, index);
            photoUrls.add(url);
        });
        return Response.ok(Map.of("photoUrls", String.join("\n", photoUrls)));
    }

    @SystemLog(businessName = "新增视频")
    @PostMapping("/video/upload")
    public Response<Map<String, String>> uploadVideo(@RequestParam("title") String title,
                                                     @RequestPart("videos") List<MultipartFile> files) {
        List<String> videoUrls = new ArrayList<>();
        String path = "demo/" + title + "/video/";
        files.forEach(file -> {
            String url = minioUtils.upload("web", path + file.getOriginalFilename(), file);
            int index = url.lastIndexOf('?');
            url = url.substring(0, index);
            videoUrls.add(url);
        });
        return Response.ok(Map.of("videoUrls", String.join("\n", videoUrls)));
    }

    @SystemLog(businessName = "新增Demo")
    @PostMapping
    public Response<Map<String, Integer>> postDemo(@RequestBody WebDemo demo) {
        webDemoService.save(demo);
        return Response.ok(Map.of("id", demo.getId()));
    }

    @SystemLog(businessName = "修改Demo")
    @PutMapping
    public Response<Object> putDemo(@RequestBody WebDemo demo) {
        webDemoService.updateById(demo);
        return Response.ok();
    }
}

