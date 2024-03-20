package edu.bistu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.bistu.mapper.WebMemberMapper;
import edu.bistu.domain.entity.WebMember;
import edu.bistu.service.WebMemberService;
import org.springframework.stereotype.Service;

/**
 * 成员管理(WebMember)表服务实现类
 *
 * @author makejava
 * @since 2024-03-20 19:49:27
 */
@Service("webMemberService")
public class WebMemberServiceImpl extends ServiceImpl<WebMemberMapper, WebMember> implements WebMemberService {

}

