package edu.bistu.controller;



import edu.bistu.service.WebMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 成员管理(WebMember)表控制层
 *
 * @author makejava
 * @since 2024-04-03 14:12:15
 */
@RestController
@RequestMapping("/webMember")
public class WebMemberController {
    /**
     * 服务对象
     */
    @Autowired
    private WebMemberService webMemberService;
}

