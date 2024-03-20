package edu.bistu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.bistu.domain.entity.WebDemo;
import org.apache.ibatis.annotations.Mapper;

/**
 * (WebDemo)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-20 19:38:05
 */
@Mapper
public interface WebDemoMapper extends BaseMapper<WebDemo> {

}

