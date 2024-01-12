package com.bytefuture.data.modules.data.service.impl;

import com.bytefuture.data.modules.data.dto.InsertDataDO;
import com.bytefuture.data.modules.data.mapper.AggregateDataMapper;
import com.bytefuture.data.modules.data.service.AggregateDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author KendrickChen
 * @date 2024/1/11  9:31
 */
@Service
@Slf4j
public class AggregateDataServiceImpl implements AggregateDataService {

    @Autowired
    private AggregateDataMapper baseMapper;

    /**
     * 清空表内数据
     * t_flow_info
     * t_flow_detail
     * t_item
     * t_item_url
     * t_scene
     */
    private void clearAll() {
        baseMapper.truncateTableInfo();
        baseMapper.truncateTableDetail();
        baseMapper.truncateTableItem();
        baseMapper.truncateTableItemUrl();
        baseMapper.truncateTableScene();
        baseMapper.truncateTableTemplate();
    }

    /**
     * 数据存入结果表中
     * t_flow_info
     * t_flow_detail
     * t_item
     * t_item_url
     * t_scene
     *
     * @return 插入条数
     */
    private InsertDataDO collectAll() {
        int flowInfo = baseMapper.collectTableInfo();
        log.info("插入的flowInfo条数：{}", flowInfo);
        int flowDetail = baseMapper.collectTableDetail();
        log.info("插入的flowDetail条数：{}", flowDetail);
        int item = baseMapper.collectTableItem();
        log.info("插入的item条数：{}", item);
        int itemUrl = baseMapper.collectTableItemUrl();
        log.info("插入的itemUrl条数：{}", itemUrl);
        int scene = baseMapper.collectTableScene();
        log.info("插入的scene条数：{}", scene);
        int template = baseMapper.collectTableTemplate();
        log.info("插入的template条数：{}", template);
        return new InsertDataDO(flowInfo, flowDetail, item, itemUrl, scene, template);

    }


    @Override
    public InsertDataDO aggregateData() {
        // 先删除
        clearAll();
        // 再添加
        return collectAll();
    }
}
