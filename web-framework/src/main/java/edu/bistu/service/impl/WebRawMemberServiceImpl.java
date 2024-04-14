package edu.bistu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.bistu.mapper.WebRawMemberMapper;
import edu.bistu.domain.entity.WebRawMember;
import edu.bistu.service.WebRawMemberService;
import org.springframework.stereotype.Service;

/**
 * (WebRawMember)表服务实现类
 *
 * @author makejava
 * @since 2024-04-14 15:12:18
 */
@Service("webRawMemberService")
public class WebRawMemberServiceImpl extends ServiceImpl<WebRawMemberMapper, WebRawMember> implements WebRawMemberService {

}

