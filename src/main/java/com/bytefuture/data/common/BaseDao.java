package com.bytefuture.data.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDao implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 创建人 */
  private String createdBy;
  /** 创建时间 */
  @TableField(fill = FieldFill.INSERT)
  private Date createdTime;
  /** 更新人 */
  private String updatedBy;
  /** 更新时间 */
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updatedTime;
  /** 删除标记 */
  @TableField(fill = FieldFill.INSERT)
  private String delFlag;
}
