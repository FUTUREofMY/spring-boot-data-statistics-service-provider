package com.bytefuture.data.modules.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author KendrickChen
 * @date 2024/1/11  19:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataDisplayDO {
    /**
     * 事项上线数量
     */
    private String itemOnlineQuantity;
    /**
     * 事项办件情况
     */
    private String itemHandleQuantity;
    /**
     * 事项办件占比
     */
    private String itemHandlePercentage;
    /**
     * 地区
     */
    private String district;

}
