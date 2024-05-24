package edu.bistu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.bistu.mapper.WebProjectMapper;
import edu.bistu.domain.entity.WebProject;
import edu.bistu.service.WebProjectService;
import org.springframework.stereotype.Service;

/**
 * (WebProject)表服务实现类
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:49:33
 */
@Service("webProjectService")
public class WebProjectServiceImpl extends ServiceImpl<WebProjectMapper, WebProject> implements WebProjectService {

}

