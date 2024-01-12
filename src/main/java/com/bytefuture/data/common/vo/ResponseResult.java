package com.bytefuture.data.common.vo;

import com.bytefuture.data.common.enums.ResponseResultStatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果
 *
 * @author zengchongchong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {

  /**
   * 响应码
   */
  private int code;
  /**
   * 界面提示信息
   */
  private String msg;
  /**
   * 响应数据
   */
  private Object data;
  /**
   * 如果响应码不正常，则此处显示后台具体代码报错异常信息，便于跟踪
   */
  private String error;

  private ResponseResult(ResponseResultStatusCodeEnum statusCodeEnum) {
    super();
    this.code = statusCodeEnum.getCode();
    this.data = statusCodeEnum.getMsg();
    this.msg = statusCodeEnum.getMsg();
  }

  private ResponseResult(ResponseResultStatusCodeEnum statusCodeEnum, Object data) {
    super();
    this.code = statusCodeEnum.getCode();
    this.msg = statusCodeEnum.getMsg();
    this.data = data;
  }

  private ResponseResult(ResponseResultStatusCodeEnum statusCodeEnum, Object data, String error) {
    super();
    this.code = statusCodeEnum.getCode();
    this.data = data;
    this.msg = statusCodeEnum.getMsg();
    this.error = error;
  }

  public static ResponseResult ok() {
    return new ResponseResult(ResponseResultStatusCodeEnum.REQUEST_SUCCESS);
  }

  public static ResponseResult ok(Object data) {
    return new ResponseResult(ResponseResultStatusCodeEnum.REQUEST_SUCCESS, data);
  }

  public static ResponseResult error(ResponseResultStatusCodeEnum statusCodeEnum, String error) {
    return new ResponseResult(statusCodeEnum, null, error);
  }

  public static ResponseResult error(
      ResponseResultStatusCodeEnum statusCodeEnum, Object data, String error) {
    return new ResponseResult(statusCodeEnum, data, error);
  }

  @Override
  public String toString() {
    return "ResponseResult [code="
        + code
        + ", msg="
        + msg
        + ", error="
        + error
        + ", data="
        + data
        + "]";
  }
}
