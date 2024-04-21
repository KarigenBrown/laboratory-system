package edu.bistu.controller;


import com.alibaba.excel.EasyExcel;
import edu.bistu.annotation.SystemLog;
import edu.bistu.domain.Response;
import edu.bistu.domain.entity.WebRawMember;
import edu.bistu.handler.listener.WebRawMemberListener;
import edu.bistu.service.WebRawMemberService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * (WebRawMember)表控制层
 *
 * @author makejava
 * @since 2024-04-21 23:00:38
 */
@PreAuthorize("hasAuthority('人员管理') || hasAnyRole('ROLE_教授', 'ROLE_副教授', 'ROLE_讲师')")
@RestController
@RequestMapping("/webRawMember")
public class WebRawMemberController {
    /**
     * 服务对象
     */
    @Autowired
    private WebRawMemberService webRawMemberService;

    @SystemLog(businessName = "新增人员原始数据")
    @SneakyThrows
    @PostMapping("/excel/upload")
    public Response<Object> uploadPhoto(@RequestPart("excel") MultipartFile file) {
        EasyExcel.read(file.getInputStream(), WebRawMember.class, new WebRawMemberListener(webRawMemberService)).sheet().doRead();
        return Response.ok();
    }
}

