package edu.bistu.controller;



import edu.bistu.service.WebProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (WebProject)表控制层
 *
 * @author makejava
 * @since 2024-03-20 19:57:34
 */
@RestController
@RequestMapping("/webProject")
public class WebProjectController {
    /**
     * 服务对象
     */
    @Autowired
    private WebProjectService webProjectService;
}

