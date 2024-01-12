package com.bytefuture.data.modules.job.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_job")
public class SysJob {

    @TableId
    private String id;

    /**
     * 描述任务
     */
    private String jobName;

    /**
     * 任务表达式
     */
    private String cron;

    /**
     * 状态:0未启动false/1启动true
     */
    private Boolean status;

    /**
     * 任务执行方法
     */
    private String clazzPath;

    /**
     * 其他描述
     */
    private String jobDesc;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;
}
