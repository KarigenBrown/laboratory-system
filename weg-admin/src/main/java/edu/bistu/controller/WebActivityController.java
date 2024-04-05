package edu.bistu.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebActivity;
import edu.bistu.service.WebActivityService;
import edu.bistu.utils.MinioUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (WebActivity)表控制层
 *
 * @author makejava
 * @since 2024-04-05 13:55:08
 */
@RestController
public class WebActivityController {
    /**
     * 服务对象
     */
    @Autowired
    private WebActivityService webActivityService;

    @Autowired
    private MinioUtils minioUtils;

    @GetMapping("/webActivity/all")
    public Response<List<WebActivity>> getAllActivities() {
        return Response.ok(webActivityService.list());
    }

    @GetMapping("/webActivity/{title}")
    public Response<List<WebActivity>> getActivitiesByTitle(@PathVariable("title") String title) {
        return Response.ok(
                webActivityService.lambdaQuery()
                        .like(WebActivity::getTitle, title)
                        .list()
        );
    }

    @DeleteMapping("/webActivity/{id}")
    public Response<Object> deleteActivityById(@PathVariable("id") Integer id) {
        webActivityService.removeById(id);
        return Response.ok();
    }

    @SneakyThrows
    @PostMapping("/webActivity/{title}/photo")
    public Response<Map<String, String>> uploadPhoto(@PathVariable("title") String title,
                                                     @RequestParam("photoName") String name,
                                                     @RequestPart("photos") List<MultipartFile> files) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> photoName = objectMapper.readValue(name, Map.class);
        List<String> urls = new ArrayList<>();
        String path = "activityPhoto/" + title + "/";
        files.forEach(file -> {
            String url = minioUtils.upload("web", path + photoName.get(file.getOriginalFilename()), file);
            int index = url.lastIndexOf('?');
            url = url.substring(0, index);
            urls.add(url);
        });
        return Response.ok(Map.of("urls", String.join("\n", urls)));
    }

    @PostMapping("/webActivity")
    public Response<Map<String, Integer>> postActivity(@RequestBody WebActivity activity) {
        webActivityService.save(activity);
        return Response.ok(Map.of("id", activity.getId()));
    }

    @PutMapping("/webActivity")
    public Response<Object> putActivity(@RequestBody WebActivity activity) {
        webActivityService.updateById(activity);
        return Response.ok();
    }
}

