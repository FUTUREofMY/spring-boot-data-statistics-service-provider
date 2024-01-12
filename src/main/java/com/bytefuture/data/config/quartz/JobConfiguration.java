package com.bytefuture.data.config.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author Administrator
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "sys.job.mode", havingValue = "quartz-job") // 目前默认走xxljob分布式定时任务，对应属性值：xxljob
public class JobConfiguration {

    @Autowired
    JobFactory jobFactory;

    @Bean(name ="schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setJobFactory(jobFactory);
        bean.setConfigLocation(new ClassPathResource("quartz.properties"));
        return bean;
    }
}
