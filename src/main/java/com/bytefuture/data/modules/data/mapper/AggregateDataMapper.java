package com.bytefuture.data.modules.data.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author KendrickChen
 * @date 2024/1/11  9:26
 */
@Mapper
public interface AggregateDataMapper {

    /**
     * 清空表数据：t_flow_info
     */
    void truncateTableInfo();
    /**
     * 清空表数据：t_flow_detail
     */
    void truncateTableDetail();
    /**
     * 清空表数据：t_item
     */
    void truncateTableItem();
    /**
     * 清空表数据：t_item_url
     */
    void truncateTableItemUrl();
    /**
     * 清空表数据：t_scene
     */
    void truncateTableScene();
    /**
     * 清空表数据：t_from_template
     */
    void truncateTableTemplate();
    /**
     * 汇入表数据：t_flow_info
     * @return 插入条数
     */
    int collectTableInfo();
    /**
     * 汇入表数据：t_flow_detail
     * @return 插入条数
     */
    int collectTableDetail();
    /**
     * 汇入表数据：t_item
     * @return 插入条数
     */
    int collectTableItem();
    /**
     * 汇入表数据：t_item_url
     * @return 插入条数
     */
    int collectTableItemUrl();
    /**
     * 汇入表数据：t_scene
     * @return 插入条数
     */
    int collectTableScene();

    /**
     * 汇入表数据：t_from_template
     * @return 插入条数
     */
    int collectTableTemplate();

}
