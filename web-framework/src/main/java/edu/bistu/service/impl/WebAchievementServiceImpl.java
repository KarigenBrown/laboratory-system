package edu.bistu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.bistu.mapper.WebAchievementMapper;
import edu.bistu.domain.entity.WebAchievement;
import edu.bistu.service.WebAchievementService;
import org.springframework.stereotype.Service;

/**
 * 论文管理(WebAchievement)表服务实现类
 *
 * @author makejava
 * @since 2024-03-20 19:48:07
 */
@Service("webAchievementService")
public class WebAchievementServiceImpl extends ServiceImpl<WebAchievementMapper, WebAchievement> implements WebAchievementService {

}

