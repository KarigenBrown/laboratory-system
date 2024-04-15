package edu.bistu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebActivity;
import edu.bistu.service.WebActivityService;
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
 * (WebActivity)表控制层
 *
 * @author makejava
 * @since 2024-04-05 13:55:08
 */
@PreAuthorize("hasAuthority('活动管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
@RestController
@RequestMapping("/webActivity")
public class WebActivityController {
    /**
     * 服务对象
     */
    @Autowired
    private WebActivityService webActivityService;

    @Autowired
    private MinioUtils minioUtils;

    @GetMapping("/all")
    public Response<List<WebActivity>> getAllActivities() {
        return Response.ok(webActivityService.list());
    }

    @GetMapping("/{title}")
    public Response<List<WebActivity>> getActivitiesByTitle(@PathVariable("title") String title) {
        return Response.ok(
                webActivityService.lambdaQuery()
                        .like(WebActivity::getTitle, title)
                        .list()
        );
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteActivityById(@PathVariable("id") Integer id) {
        webActivityService.removeById(id);
        return Response.ok();
    }

    @SneakyThrows
    @PostMapping("/{title}/photo/upload")
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

    @GetMapping("/{title}/photo/download/{filename}")
    public void downloadPhoto(@PathVariable("title") String title,
                              @PathVariable("filename") String filename,
                              HttpServletResponse response) {
        String name = "activityPhoto/" + title + "/" + filename;
        minioUtils.download("web", name, response);
    }

    @PostMapping
    public Response<Map<String, Integer>> postActivity(@RequestBody WebActivity activity) {
        webActivityService.save(activity);
        return Response.ok(Map.of("id", activity.getId()));
    }

    @PutMapping
    public Response<Object> putActivity(@RequestBody WebActivity activity) {
        webActivityService.updateById(activity);
        return Response.ok();
    }
}

