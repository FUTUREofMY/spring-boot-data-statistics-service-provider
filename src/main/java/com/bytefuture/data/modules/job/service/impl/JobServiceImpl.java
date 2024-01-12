package com.bytefuture.data.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytefuture.data.modules.job.domain.SysJob;
import com.bytefuture.data.modules.job.mapper.SysJobMapper;
import com.bytefuture.data.modules.job.service.JobService;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements JobService {

}
