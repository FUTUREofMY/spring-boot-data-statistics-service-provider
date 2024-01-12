package com.bytefuture.data.job;

import com.bytefuture.data.modules.data.dto.InsertDataDO;
import com.bytefuture.data.modules.data.service.AggregateDataService;
import com.bytefuture.data.modules.item.domain.ItemSituation;
import com.bytefuture.data.modules.item.service.ItemSituationService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author KendrickChen
 * @date 2023/5/22  11:58
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class AggregatingDataScheduleJob {

    @Autowired
    private AggregateDataService aggregateDataService;
    @Autowired
    private ItemSituationService itemSituationService;

    public void aggregatingDataScheduleJob() throws Exception {
        log.info("-------------定时任务（AggregatingDataScheduleJob）开始执行-------------");
        InsertDataDO insertDataDO = aggregateDataService.aggregateData();
        log.info("执行条数：{}", insertDataDO);
        List<ItemSituation> itemSituationData = itemSituationService.getItemSituationData();
        log.info("插入条数：{}", itemSituationData);
        log.info("-------------定时任务（AggregatingDataScheduleJob）结束执行-------------");
    }

}
