package com.bytefuture.data.config.quartz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bytefuture.data.modules.job.domain.SysJob;
import com.bytefuture.data.modules.job.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@ConditionalOnProperty(name = "sys.job.mode", havingValue = "quartz-job") // 目前默认走xxljob分布式定时任务，对应属性值：xxljob
public class JobThread extends Thread {

    @Autowired
    JobService jobService;

    @Autowired
    JobTask jobTask;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            log.info("---------quartz定时任务自定义辅助线程启动---------");
            List<SysJob> jobList = jobService.list(new QueryWrapper<SysJob>().eq("status", "1"));
            //开启任务
            jobList.forEach(job -> {
                    log.info("---quartz定时任务[" + job.getId() + "]系统 init--开始启动---------");
                    jobTask.startJob(job);
                }
            );
            if (jobList.size() == 0) {
                log.info("---数据库暂无启动的quartz定时任务---------");
            } else {
                log.info("---quartz定时任务启动完毕---------");
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}
