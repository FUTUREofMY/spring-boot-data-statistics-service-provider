package com.bytefuture.data.handler;

import com.bytefuture.data.common.enums.ResponseResultStatusCodeEnum;
import com.bytefuture.data.common.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 拦截所有@RequestMapping方法的抛异常情形并执行指定的处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalRequestExceptionHandler {

    /**
     * 通用异常提醒
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e, HttpServletRequest request) {
        log.info("request URL:" + request.getRequestURL());
        log.error(e.getMessage(), e);
        return ResponseResult.error(ResponseResultStatusCodeEnum.REQUEST_FAILED, e.getMessage());
    }

    /*********************************************接口请求异常类**************************************************/

    /**
     * 接口请求参数类型、个数不对异常提醒
     **/
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseResult handleMethodParam(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.info("request URL:" + request.getRequestURL());
        log.error(e.getMessage(), e);
        return ResponseResult.error(ResponseResultStatusCodeEnum.REQUEST_FAILED, "前端传入参数" + e.getName() + "不符合接口格式");
    }

    /**
     * 接口请求方式不对异常提醒
     **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.info("request URL:" + request.getRequestURL());
        log.error(e.getMessage(), e);
        return ResponseResult.error(ResponseResultStatusCodeEnum.REQUEST_FAILED, "请求地址'" + request.getRequestURL() + "',不支持'" + e.getMethod() + "'请求");
    }

    /**
     * 接口路径不对异常提醒
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResult handlerNoFoundException(Exception e, HttpServletRequest request) {
        log.info("request URL:" + request.getRequestURL());
        log.error(e.getMessage(), e);
        return ResponseResult.error(ResponseResultStatusCodeEnum.REQUEST_FAILED, "请求地址'" + request.getRequestURL() + "',不存在，请检查路径是否正确");
    }

    /**
     * 方法参数无效
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public ResponseResult handleValidated(Exception e, HttpServletRequest request) {
        List<FieldError> fieldErrors = null;
        if (e instanceof BindException){
            BindException e1=(BindException) e;
            fieldErrors= e1.getBindingResult().getFieldErrors();
        }else {
            MethodArgumentNotValidException e2 = (MethodArgumentNotValidException) e;
            fieldErrors =e2.getBindingResult().getFieldErrors();
        }
        List<String> validationResults = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            validationResults.add(fieldError.getDefaultMessage());
        }
        String messages = StringUtils.join(Arrays.asList(validationResults.toArray()), ";");
        log.info("request URL:" + request.getRequestURL());
        log.error("方法参数无效:" + messages);
        return ResponseResult.error(ResponseResultStatusCodeEnum.REQUEST_FAILED, messages);
    }

    /**
     * 请求参数异常
     **/
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseResult handleParamException(ConstraintViolationException e, HttpServletRequest request) {
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(Arrays.asList(msgList.toArray()), ";");
        log.info("request URL:" + request.getRequestURL());
        log.error("请求参数异常:" + messages);
        return ResponseResult.error(ResponseResultStatusCodeEnum.REQUEST_FAILED, messages);
    }

    /*************************************************SQL异常类*****************************************************/

    /**
     * 通用SQL异常提醒
     */
    @ExceptionHandler(SQLException.class)
    public ResponseResult handlerSqlException(SQLException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.error(ResponseResultStatusCodeEnum.REQUEST_FAILED,"服务运行SQL异常：" + e.getMessage());
    }

    /**
     * SQL记录已存在异常提醒
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseResult handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.error(ResponseResultStatusCodeEnum.REQUEST_FAILED, "数据库中已存在该记录");
    }
}

