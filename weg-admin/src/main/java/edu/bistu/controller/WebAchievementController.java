package edu.bistu.controller;


import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebAchievement;
import edu.bistu.service.WebAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 论文管理(WebAchievement)表控制层
 *
 * @author makejava
 * @since 2024-04-07 14:18:28
 */
@RestController
@RequestMapping("/webAchievement")
public class WebAchievementController {
    /**
     * 服务对象
     */
    @Autowired
    private WebAchievementService webAchievementService;

    @GetMapping("/all")
    public Response<List<WebAchievement>> getAllAchievements() {
        return Response.ok(webAchievementService.list());
    }

    @GetMapping("/{category}/all")
    public Response<List<WebAchievement>> getAllAchievementsByCategory(@PathVariable("category") String category) {
        return Response.ok(
                webAchievementService.lambdaQuery()
                        .eq(WebAchievement::getCategory, category)
                        .list()
        );
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteAchievementById(@PathVariable("id") Integer id) {
        webAchievementService.removeById(id);
        return Response.ok();
    }

    @PostMapping
    public Response<Map<String, Integer>> postAchievement(@RequestBody WebAchievement achievement) {
        webAchievementService.save(achievement);
        return Response.ok(Map.of("id", achievement.getId()));
    }

    @PutMapping
    public Response<Object> putAchievement(@RequestBody WebAchievement achievement) {
        webAchievementService.updateById(achievement);
        return Response.ok();
    }

    @GetMapping("/{category}/type/{type}/{info}")
    public Response<List<WebAchievement>> getAchievementsByType(@PathVariable("category") String category,
                                                                @PathVariable("type") String type,
                                                                @PathVariable("info") String info) {
        return Response.ok(
                webAchievementService.lambdaQuery()
                        .eq(WebAchievement::getCategory, category)
                        .like(type.equals("标题"), WebAchievement::getTitle, info)
                        .eq(type.equals("年份"), WebAchievement::getTheyear, info)
                        .like(type.equals("期刊（首字母）"), WebAchievement::getInitials, info)
                        .like(type.equals("作者"), WebAchievement::getAuthor, info)
                        .or(type.equals("作者")).like(type.equals("作者"), WebAchievement::getAuthors, info)
                        .list()
        );
    }

    @GetMapping("/{category}/{title}")
    public Response<List<WebAchievement>> getAchievementsByTitle(@PathVariable("category") String category,
                                                                 @PathVariable("title") String title) {
        return Response.ok(
                webAchievementService.lambdaQuery()
                        .eq(WebAchievement::getCategory, category)
                        .like(WebAchievement::getTitle, title)
                        .list()
        );
    }
}

