package edu.bistu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.bistu.mapper.WebDemoMapper;
import edu.bistu.domain.entity.WebDemo;
import edu.bistu.service.WebDemoService;
import org.springframework.stereotype.Service;

/**
 * (WebDemo)表服务实现类
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:48:45
 */
@Service("webDemoService")
public class WebDemoServiceImpl extends ServiceImpl<WebDemoMapper, WebDemo> implements WebDemoService {

}

