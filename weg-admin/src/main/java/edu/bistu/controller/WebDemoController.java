package edu.bistu.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebDemo;
import edu.bistu.service.WebDemoService;
import edu.bistu.utils.MinioUtils;
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
@PreAuthorize("hasAuthority('Demo管理')")
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

    @GetMapping("/all")
    public Response<List<WebDemo>> getAllDemos() {
        return Response.ok(webDemoService.list());
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteActivityById(@PathVariable("id") Integer id) {
        webDemoService.removeById(id);
        return Response.ok();
    }

    @SneakyThrows
    @PostMapping("/{title}/photo/upload")
    public Response<Map<String, String>> uploadPhoto(@PathVariable("title") String title,
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

    @PostMapping("/{title}/video/upload")
    public Response<Map<String, String>> uploadVideo(@PathVariable("title") String title,
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

    @GetMapping("/{title}/photo/download/{filename}")
    public void downloadPhoto(@PathVariable("title") String title,
                              @PathVariable("filename") String filename,
                              HttpServletResponse response) {
        String name = "demo/" + title + "/photo/" + filename;
        minioUtils.download("web", name, response);
    }

    @GetMapping("/{title}/video/download/{filename}")
    public void downloadVideo(@PathVariable("title") String title,
                              @PathVariable("filename") String filename,
                              HttpServletResponse response) {
        String name = "demo/" + title + "/video/" + filename;
        minioUtils.download("web", name, response);
    }

    @PostMapping
    public Response<Map<String, Integer>> postDemo(@RequestBody WebDemo demo) {
        webDemoService.save(demo);
        return Response.ok(Map.of("id", demo.getId()));
    }

    @PutMapping
    public Response<Object> putDemo(@RequestBody WebDemo demo) {
        webDemoService.updateById(demo);
        return Response.ok();
    }
}

