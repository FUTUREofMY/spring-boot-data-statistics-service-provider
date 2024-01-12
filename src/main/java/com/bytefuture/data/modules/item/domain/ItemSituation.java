package com.bytefuture.data.modules.item.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.bytefuture.data.common.BaseDao;
import lombok.Data;

/**
 * 事项情况
 *
 * @author kchen
 * @email galen1711@126.com
 * @date 2024-01-11 11:17:57
 */
@Data
@TableName("t_item_situation")
public class ItemSituation extends BaseDao implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 排名
	 */
	private Integer ranking;
	/**
	 * 行政区划
	 */
	private String administrativeDivision;
	/**
	 * 总事项数量
	 */
	private Integer totalItemsCount;
	/**
	 * 事项上线数量
	 */
	private Integer onlineItemsCount;
	/**
	 * 外链数量
	 */
	private Integer externalLinksCount;
	/**
	 * 开发事项数量
	 */
	private Integer developmentItemsCount;
	/**
	 * 有办件事项数量
	 */
	private Integer processedItemsCount;

}
