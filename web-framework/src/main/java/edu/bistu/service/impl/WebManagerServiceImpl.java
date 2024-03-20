package edu.bistu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.bistu.mapper.WebManagerMapper;
import edu.bistu.domain.entity.WebManager;
import edu.bistu.service.WebManagerService;
import org.springframework.stereotype.Service;

/**
 * (WebManager)表服务实现类
 *
 * @author makejava
 * @since 2024-03-20 19:49:20
 */
@Service("webManagerService")
public class WebManagerServiceImpl extends ServiceImpl<WebManagerMapper, WebManager> implements WebManagerService {

}

