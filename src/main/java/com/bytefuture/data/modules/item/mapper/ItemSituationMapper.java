package com.bytefuture.data.modules.item.mapper;


import com.bytefuture.data.modules.item.domain.ItemSituation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bytefuture.data.modules.item.dto.DataDisplayDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * 事项情况
 *
 * @author kchen
 * @email galen1711@126.com
 * @date 2024-01-11 11:17:57
 */
@Mapper
public interface ItemSituationMapper extends BaseMapper<ItemSituation> {

    /**
     * @return 事项相关情况数据获取
     */
    List<ItemSituation> getItemSituationData();

    /**
     * 清空表数据
     */
    void truncateTableData();


    List<DataDisplayDO> dataDisplay();

}
