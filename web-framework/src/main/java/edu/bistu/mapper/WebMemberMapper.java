package edu.bistu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.bistu.domain.entity.WebMember;
import org.apache.ibatis.annotations.Mapper;

/**
 * 成员管理(WebMember)表数据库访问层
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:44:36
 */
@Mapper
public interface WebMemberMapper extends BaseMapper<WebMember> {

}

