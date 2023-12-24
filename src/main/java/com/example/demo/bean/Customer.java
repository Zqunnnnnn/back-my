package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author Zqunnnnnn
 * @since 2023-12-09
 */
@ApiModel(value = "Customer对象", description = "")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "c_id", type = IdType.AUTO)
      private Integer cId;

    private String cName;

    public Integer getcId() {
        return cId;
    }

      public void setcId(Integer cId) {
          this.cId = cId;
      }

    public String getcName() {
        return cName;
    }

      public void setcName(String cName) {
          this.cName = cName;
      }

    @Override
    public String toString() {
        return "Customer{" +
              "cId = " + cId +
                  ", cName = " + cName +
              "}";
    }
}
