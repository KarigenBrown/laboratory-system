package edu.bistu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.bistu.mapper.WebVisitorsMapper;
import edu.bistu.domain.entity.WebVisitors;
import edu.bistu.service.WebVisitorsService;
import org.springframework.stereotype.Service;

/**
 * 访问管理(WebVisitors)表服务实现类
 *
 * @author makejava
 * @since 2024-03-20 19:49:39
 */
@Service("webVisitorsService")
public class WebVisitorsServiceImpl extends ServiceImpl<WebVisitorsMapper, WebVisitors> implements WebVisitorsService {

}

