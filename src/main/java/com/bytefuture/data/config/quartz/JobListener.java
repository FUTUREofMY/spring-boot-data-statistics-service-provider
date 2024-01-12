package com.bytefuture.data.config.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "sys.job.mode", havingValue = "quartz-job") // 目前默认走xxljob分布式定时任务，对应属性值：xxljob
public class JobListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!event.getApplicationContext().getDisplayName().contains("FeignContext-")
            && !event.getApplicationContext().getDisplayName().contains("LoadBalancerClientFactory-")) {
            JobThread myThread = (JobThread) event.getApplicationContext().getBean("jobThread");
            myThread.start();
        }
    }
}
