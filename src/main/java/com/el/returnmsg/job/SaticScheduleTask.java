package com.el.returnmsg.job;

import com.el.returnmsg.service.F551513ZService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Created by Alan on 2020/10/14 18:42
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    @Resource
    F551513ZService f551513ZService;
    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {

//        f551513ZService.queryF551513ZList("N","A");
//        f551513ZService.queryF551513ZList("N","P");

    }
}