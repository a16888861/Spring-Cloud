package com.lucky.kali.common.Scheduled;

import com.lucky.kali.common.util.LocalDateTimeUtil;
import org.jboss.logging.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Elliot
 */
@Component
@EnableScheduling
public class CommonScheduled {

    private static final Logger log = Logger.getLogger(CommonScheduled.class);

    /**
     * 每60秒执行一次
     */
    @Scheduled(cron = "0/60 * * * * ? ")
    public void aTask() {
        /*测试任务*/
        log.info("********* 现在时间是 " + LocalDateTimeUtil.localDateTimeToDate(LocalDateTime.now()) + " *********");
    }
}
