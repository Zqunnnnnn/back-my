package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author Zqu
 * @since 2024-02-28
 */
@Getter
@Setter
  @TableName("sys_fix")
@ApiModel(value = "Fix对象", description = "")
public class Fix implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fix_id", type = IdType.AUTO)
    private Integer fixId;

    private Integer empId;

    private String remark;

    private boolean status;

    private LocalDateTime date;
}
