package edu.bistu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.bistu.annotation.SystemLog;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebAchievement;
import edu.bistu.service.WebAchievementService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 论文管理(WebAchievement)表控制层
 *
 * @author makejava
 * @since 2024-04-07 14:18:28
 */
@RateLimiter(name = "default")
@PreAuthorize("hasAuthority('成果管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
@RestController
@RequestMapping("/webAchievement")
public class WebAchievementController {
    /**
     * 服务对象
     */
    @Autowired
    private WebAchievementService webAchievementService;

    @GetMapping("/{category}/all/{pageSize}/{currentPage}")
    public Response<Map<String, Object>> getAllAchievementsByCategory(@PathVariable("pageSize") Integer pageSize,
                                                                       @PathVariable("currentPage") Integer currentPage,
                                                                       @PathVariable("category") String category) {
        Page<WebAchievement> page = webAchievementService.lambdaQuery()
                .eq(WebAchievement::getCategory, category)
                .page(new Page<>(currentPage, pageSize));
        return Response.ok(Map.of(
                "rows", page.getRecords(),
                "total", page.getTotal()
        ));
    }

    @SystemLog(businessName = "删除一个成就")
    @DeleteMapping("/{id}")
    public Response<Object> deleteAchievementById(@PathVariable("id") Integer id) {
        webAchievementService.removeById(id);
        return Response.ok();
    }

    @SystemLog(businessName = "新增一个成就")
    @PostMapping
    public Response<Map<String, Integer>> postAchievement(@RequestBody WebAchievement achievement) {
        webAchievementService.save(achievement);
        return Response.ok(Map.of("id", achievement.getId()));
    }

    @SystemLog(businessName = "修改一个成就")
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

