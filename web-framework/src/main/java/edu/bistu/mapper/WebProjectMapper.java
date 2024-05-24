package edu.bistu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.bistu.domain.entity.WebProject;
import org.apache.ibatis.annotations.Mapper;

/**
 * (WebProject)表数据库访问层
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:44:51
 */
@Mapper
public interface WebProjectMapper extends BaseMapper<WebProject> {

}

