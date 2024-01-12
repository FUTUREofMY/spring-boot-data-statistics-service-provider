package com.bytefuture.data.modules.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bytefuture.data.modules.item.domain.ItemSituation;
import com.bytefuture.data.modules.item.dto.DataDisplayDO;

import java.util.List;

/**
 * 事项情况
 *
 * @author kchen
 * @email galen1711@126.com
 * @date 2024-01-11 11:17:57
 */
public interface ItemSituationService extends IService<ItemSituation> {

    /**
     * @return 事项相关情况数据获取
     */
    List<ItemSituation> getItemSituationData();

    List<DataDisplayDO> dataDisplay();

}

