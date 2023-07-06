package org.laocat.handler;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.laocat.dispatch.LaoCatXxlJobConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @description: 任务开发类
 * @author: LaoCat
 * @date: 2023/4/3
 */
@Component
@Slf4j
public class DispatchHandler {


    /**
     * @description: 测试示例
     * @author: LaoCat
     * @date: 2023/4/3
     * @returnType: void
     */
    @XxlJob("testBar")
    public void testBar() throws Exception {
        log.info("Welcome to Cat Home");

        for (int i = 0; i < 5; i++) {
            log.info("{}喵 ~ ", i);
        }
    }


}
