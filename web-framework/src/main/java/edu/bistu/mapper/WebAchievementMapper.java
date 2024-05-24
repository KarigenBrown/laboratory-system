package edu.bistu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.bistu.domain.entity.WebAchievement;
import org.apache.ibatis.annotations.Mapper;

/**
 * 论文管理(WebAchievement)表数据库访问层
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:33:15
 */
@Mapper
public interface WebAchievementMapper extends BaseMapper<WebAchievement> {

}

