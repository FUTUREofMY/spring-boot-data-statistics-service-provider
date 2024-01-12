package com.bytefuture.data.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author min.yang
 * @date 16:11 2022/3/15
 * @param
 * @return
 */
@Component
public class MybatisPlusHandler implements MetaObjectHandler {

  /** 使用mp实现添加操作，这个方法执行 createTime为字段 new Date()为现在时间 metaObject */
  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("createdTime", new Date(), metaObject);
    this.setFieldValByName("updatedTime", new Date(), metaObject);
    this.setFieldValByName("delFlag", "0", metaObject);
  }

  /** 使用mp实现修改操作，这个方法执行 */
  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updatedTime", new Date(), metaObject);
  }
}
