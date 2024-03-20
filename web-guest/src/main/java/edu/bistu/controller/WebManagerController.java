package edu.bistu.controller;



import edu.bistu.service.WebManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (WebManager)表控制层
 *
 * @author makejava
 * @since 2024-03-20 19:57:13
 */
@RestController
@RequestMapping("/webManager")
public class WebManagerController {
    /**
     * 服务对象
     */
    @Autowired
    private WebManagerService webManagerService;
}

