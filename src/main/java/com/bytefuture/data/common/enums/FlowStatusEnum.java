package com.bytefuture.data.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 响应码信息
 * @author KendrickChen
 * @date 2023/5/11  11:02
 */
@Getter
@AllArgsConstructor
public enum FlowStatusEnum {

    /**
     * 0：待提交
     */
    FLOW_NO_PUSH_STATUS("0", "待提交"),
    /**
     * 1：已提交
     */
    FLOW_ALREADY_STATUS("1", "已提交"),
    /**
     * 2：补齐补正
     */
    FLOW_CORRECT_STATUS("2","补齐补正"),
    /**
     * 3：整改
     */
    FLOW_ALTER_STATUS("3","整改"),
    /**
     * 4：自行退回
     */
    FLOW_SELF_BACK_STATUS("4","自行退回"),
    /**
     * 5：审批端退回
     */
    FLOW_APPROVAL_RETURN_STATUS("5","审批端退回"),
    /**
     * 6：中途办结
     */
    FLOW_APPROVAL_HALF_STATUS("5","审批端退回"),
    /**
     * 8：已受理，正在进行中
     */
    FLOW_ACCEPT_STATUS("8", "已受理"),
    /**
     * 9：准予结论，办结
     */
    FLOW_END_ACCEPT_STATUS("9", "准予结论"),
    /**
     * -1：不予受理
     */
    FLOW_FAILED_STATUS("-1", "不予受理"),
    /**
     * -2：不予结论
     */
    FLOW_REFUSE_STATUS("-2", "不予结论");

    private final String value;

    private final String name;
}
