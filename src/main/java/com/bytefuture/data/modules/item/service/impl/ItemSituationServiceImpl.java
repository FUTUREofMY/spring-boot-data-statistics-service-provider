package com.bytefuture.data.modules.item.service.impl;

import com.bytefuture.data.modules.item.dto.DataDisplayDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytefuture.data.modules.item.domain.ItemSituation;
import com.bytefuture.data.modules.item.mapper.ItemSituationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bytefuture.data.modules.item.service.ItemSituationService;

import java.util.List;


/**
 * @author Administrator
 */
@Service
public class ItemSituationServiceImpl extends ServiceImpl<ItemSituationMapper, ItemSituation> implements ItemSituationService {

    @Autowired
    private ItemSituationMapper baseMapper;

    @Override
    public List<ItemSituation> getItemSituationData() {
        baseMapper.truncateTableData();
        List<ItemSituation> itemSituationData = baseMapper.getItemSituationData();
        int i = 1;
        for (ItemSituation itemSituation : itemSituationData) {
            itemSituation.setRanking(i);
            baseMapper.insert(itemSituation);
            i++;
        }

        return itemSituationData;
    }

    @Override
    public List<DataDisplayDO> dataDisplay() {

        return baseMapper.dataDisplay();
    }
}
