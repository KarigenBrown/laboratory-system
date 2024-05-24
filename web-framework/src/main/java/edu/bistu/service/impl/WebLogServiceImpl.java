package edu.bistu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.bistu.mapper.WebLogMapper;
import edu.bistu.domain.entity.WebLog;
import edu.bistu.service.WebLogService;
import org.springframework.stereotype.Service;

/**
 * (WebLog)表服务实现类
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:49:11
 */
@Service("webLogService")
public class WebLogServiceImpl extends ServiceImpl<WebLogMapper, WebLog> implements WebLogService {

}

