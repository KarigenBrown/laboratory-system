package edu.bistu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.bistu.domain.entity.WebManager;
import org.apache.ibatis.annotations.Mapper;

/**
 * (WebManager)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-20 19:43:38
 */
@Mapper
public interface WebManagerMapper extends BaseMapper<WebManager> {

}

