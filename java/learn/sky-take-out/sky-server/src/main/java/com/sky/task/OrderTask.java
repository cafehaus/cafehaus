package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class OrderTask {

    /**
     * 订单超时定时任务处理测试
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void processTimeoutOrder() {
        // log.info("订单超时处理:{}", LocalDateTime.now());
    }
}
