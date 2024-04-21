package edu.bistu.handler.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import edu.bistu.domain.entity.WebRawMember;
import edu.bistu.service.WebRawMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
public class WebRawMemberListener implements ReadListener<WebRawMember> {
    private static final int BATCH_COUNT = 5;

    private List<WebRawMember> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private WebRawMemberService webRawMemberService;

    public WebRawMemberListener(WebRawMemberService webRawMemberService) {
        this.webRawMemberService = webRawMemberService;
    }

    @Override
    public void invoke(WebRawMember webRawMember, AnalysisContext analysisContext) {
        cachedDataList.add(webRawMember);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }

    private void saveData() {
        webRawMemberService.saveBatch(cachedDataList);
    }
}
