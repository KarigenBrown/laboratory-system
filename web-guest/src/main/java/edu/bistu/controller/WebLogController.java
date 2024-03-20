package edu.bistu.controller;


import edu.bistu.domain.entity.WebLog;
import edu.bistu.service.WebLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (WebLog)表控制层
 *
 * @author makejava
 * @since 2024-03-20 19:57:05
 */
@RestController
@RequestMapping("/webLog")
public class WebLogController {
    /**
     * 服务对象
     */
    @Autowired
    private WebLogService webLogService;

    @GetMapping("/")
    public String index() {
        WebLog byId = webLogService.getById(1);
        System.out.println(byId.getLog());
        return "ok";
    }
}

