package com.simons.cn.springbootdemo.task;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.task
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/28 17:59
 */
@Component
public class PrintTimeTask {

    private static final Logger logger = LoggerFactory.getLogger(PrintTimeTask.class);

    @Scheduled(cron = "*/1 1 1 * * ?")
    public void schedulePrint() {
        logger.info("当前时间为:{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

}
