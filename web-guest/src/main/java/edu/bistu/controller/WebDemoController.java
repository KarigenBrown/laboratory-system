package edu.bistu.controller;



import edu.bistu.service.WebDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (WebDemo)表控制层
 *
 * @author makejava
 * @since 2024-03-20 19:56:58
 */
@RestController
@RequestMapping("/webDemo")
public class WebDemoController {
    /**
     * 服务对象
     */
    @Autowired
    private WebDemoService webDemoService;
}

