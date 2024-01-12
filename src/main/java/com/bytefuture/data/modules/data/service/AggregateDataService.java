package com.bytefuture.data.modules.data.service;

import com.bytefuture.data.modules.data.dto.InsertDataDO;

/**
 * @author KendrickChen
 * @date 2024/1/11  9:29
 */
public interface AggregateDataService{
    /**
     * 数据交互
     *
     * @return
     */
    InsertDataDO aggregateData();
}
