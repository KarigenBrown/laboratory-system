package edu.bistu.controller;



import edu.bistu.service.WebAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 论文管理(WebAchievement)表控制层
 *
 * @author makejava
 * @since 2024-03-20 19:56:35
 */
@RestController
@RequestMapping("/webAchievement")
public class WebAchievementController {
    /**
     * 服务对象
     */
    @Autowired
    private WebAchievementService webAchievementService;
}

