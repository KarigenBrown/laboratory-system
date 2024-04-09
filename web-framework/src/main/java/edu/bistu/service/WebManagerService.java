package edu.bistu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.bistu.domain.entity.WebManager;

import java.util.Map;

/**
 * (WebManager)表服务接口
 *
 * @author makejava
 * @since 2024-03-20 19:49:20
 */
public interface WebManagerService extends IService<WebManager> {

    Map<String, String> login(WebManager manager);
}

