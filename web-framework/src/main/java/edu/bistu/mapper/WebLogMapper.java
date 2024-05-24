package edu.bistu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.bistu.domain.entity.WebLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * (WebLog)表数据库访问层
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:38:42
 */
@Mapper
public interface WebLogMapper extends BaseMapper<WebLog> {

}

