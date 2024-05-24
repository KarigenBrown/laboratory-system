package edu.bistu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.bistu.domain.entity.WebVisitors;
import org.apache.ibatis.annotations.Mapper;

/**
 * 访问管理(WebVisitors)表数据库访问层
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:45:00
 */
@Mapper
public interface WebVisitorsMapper extends BaseMapper<WebVisitors> {

}

