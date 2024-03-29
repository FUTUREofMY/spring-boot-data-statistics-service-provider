package com.bytefuture.data.config.quartz;

import com.bytefuture.data.modules.job.domain.SysJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

/**
 *
 * 定时任务类 增删改 可参考api：http://www.quartz-scheduler.org/api/2.2.1/
 * @author Administrator
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "sys.job.mode", havingValue = "quartz-job") // 目前默认走xxljob分布式定时任务，对应属性值：xxljob
public class JobTask {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    /**
     * true 存在 false 不存在
     *
     * @param
     * @return
     */
    public boolean checkJob(SysJob job) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getId(), Scheduler.DEFAULT_GROUP);
        try {
            if (scheduler.checkExists(triggerKey)) {
                return true;
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 开启
     */
    public boolean startJob(SysJob job) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            Class clazz = Class.forName(job.getClazzPath());
            JobDetail jobDetail = JobBuilder.newJob(clazz).build();
            // 触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getId(), Scheduler.DEFAULT_GROUP);
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).build();
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
                log.info("---quartz定时任务[" + triggerKey.getName() + "]启动成功-------");
                return true;
            } else {
                log.info("---quartz定时任务[" + triggerKey.getName() + "]已经运行，请勿再次启动-------");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * 更新
     */
    public boolean updateJob(SysJob job) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        String createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");

        TriggerKey triggerKey = TriggerKey.triggerKey(job.getId(), Scheduler.DEFAULT_GROUP);
        try {
            if (scheduler.checkExists(triggerKey)) {
                return false;
            }

            JobKey jobKey = JobKey.jobKey(job.getId(), Scheduler.DEFAULT_GROUP);

            CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(job.getCron())
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                    .withDescription(createTime).withSchedule(schedBuilder).build();
            Class clazz = null;
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(trigger);
            scheduler.scheduleJob(jobDetail, triggerSet, true);
            log.info("---quartz定时任务[" + triggerKey.getName() + "]更新成功-------");
            return true;
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除
     */
    public boolean remove(SysJob job) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getId(), Scheduler.DEFAULT_GROUP);
        try {
            if (checkJob(job)) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                scheduler.deleteJob(JobKey.jobKey(job.getId(), Scheduler.DEFAULT_GROUP));
                log.info("---quartz定时任务[" + triggerKey.getName() + "]删除成功-------");
                return true;
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
