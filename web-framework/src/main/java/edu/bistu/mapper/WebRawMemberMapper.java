package edu.bistu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.bistu.domain.entity.WebRawMember;
import org.apache.ibatis.annotations.Mapper;

/**
 * (WebRawMember)表数据库访问层
 *
 * @author KarigenBrown
 * @since 2024-04-14 15:12:37
 */
@Mapper
public interface WebRawMemberMapper extends BaseMapper<WebRawMember> {

}

