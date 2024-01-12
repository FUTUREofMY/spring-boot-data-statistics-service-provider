package com.bytefuture.data.common.enums;

import java.util.Objects;

/**
 * 响应码信息
 * @author KendrickChen
 * @date 2023/5/11  11:02
 */
public enum ResponseResultStatusCodeEnum {

    /**
     * 请求成功
     */
    REQUEST_SUCCESS(0, "请求成功"),
    /**
     * 初次提交成功
     */
    FIRST_SUCCESS(201, "初次提交成功"),
    /**
     * 再次提交成功
     */
    AGAIN_SUCCESS(202, "再次提交成功"),
    /**
     * 补齐补正提交成功
     */
    CORRECT_SUCCESS(203, "补齐补正提交成功"),
    /**
     * 请求失败
     */
    REQUEST_FAILED(500, "请求失败"),
    /**
     * 系统繁忙
     */
    SERVER_BUSINESS(-9999, "系统繁忙");

    /** 状态码 */
    private final Integer code;

    /** 状态码对应的信息 */
    private final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseResultStatusCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String match(Integer code) {
        for ( ResponseResultStatusCodeEnum typeVar : ResponseResultStatusCodeEnum.values()) {
            if (Objects.equals(typeVar.code,code)) {
                return typeVar.msg;
            }
        }
        return SERVER_BUSINESS.msg;
    }
}
