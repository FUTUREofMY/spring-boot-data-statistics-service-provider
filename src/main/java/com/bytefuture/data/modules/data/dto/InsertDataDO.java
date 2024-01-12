package com.bytefuture.data.modules.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author KendrickChen
 * @date 2024/1/11  11:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertDataDO {
    /**
     * 主流程插入条数
     */
    private Integer flowInfo;
    /**
     * 子流程插入条数
     */
    private Integer flowDetail;
    /**
     * 事项表插入条数
     */
    private Integer item;
    /**
     * 事项外链插入条数
     */
    private Integer itemUrl;
    /**
     * 场景表插入条数
     */
    private Integer scene;
    /**
     * 表单数据插入条数
     */
    private Integer template;
}
