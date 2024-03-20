package edu.bistu.controller;



import edu.bistu.service.WebActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (WebActivity)表控制层
 *
 * @author makejava
 * @since 2024-03-20 19:56:51
 */
@RestController
@RequestMapping("/webActivity")
public class WebActivityController {
    /**
     * 服务对象
     */
    @Autowired
    private WebActivityService webActivityService;
}

