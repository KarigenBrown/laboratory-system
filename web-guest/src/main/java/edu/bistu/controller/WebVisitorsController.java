package edu.bistu.controller;


import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebVisitors;
import edu.bistu.service.WebVisitorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 访问管理(WebVisitors)表控制层
 *
 * @author KarigenBrown
 * @since 2024-03-20 19:57:43
 */
@RestController
@RequestMapping("/webVisitors")
public class WebVisitorsController {
    /**
     * 服务对象
     */
    @Autowired
    private WebVisitorsService webVisitorsService;

    @Transactional
    @RequestMapping("/")
    public Response<Integer> count() {
        WebVisitors visitorsNum = webVisitorsService.getById(1);
        int newVisitorsNum = visitorsNum.getVisitornum() + 1;
        webVisitorsService.lambdaUpdate()
                .eq(WebVisitors::getId, 1)
                .set(WebVisitors::getVisitornum, newVisitorsNum)
                .update();
        return Response.ok(newVisitorsNum);
    }

}

