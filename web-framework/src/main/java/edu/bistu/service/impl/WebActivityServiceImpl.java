package edu.bistu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.bistu.mapper.WebActivityMapper;
import edu.bistu.domain.entity.WebActivity;
import edu.bistu.service.WebActivityService;
import org.springframework.stereotype.Service;

/**
 * (WebActivity)表服务实现类
 *
 * @author makejava
 * @since 2024-03-20 19:48:28
 */
@Service("webActivityService")
public class WebActivityServiceImpl extends ServiceImpl<WebActivityMapper, WebActivity> implements WebActivityService {

}

