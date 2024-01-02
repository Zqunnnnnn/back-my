package com.example.demo.bean;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author Zqu
 * @since 2023-12-09
 */
@ApiModel(value = "Wife对象", description = "")
@AllArgsConstructor
public class Wife implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer wid;

    private String wname;

    public Integer getWid() {
        return wid;
    }

      public void setWid(Integer wid) {
          this.wid = wid;
      }

    public String getWname() {
        return wname;
    }

      public void setWname(String wname) {
          this.wname = wname;
      }

    @Override
    public String toString() {
        return "Wife{" +
              "wid = " + wid +
                  ", wname = " + wname +
              "}";
    }
}
