package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @TableName emp
 */
@TableName(value ="emp")
@Data
@ToString
public class Emp implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO,value = "emp_id")
    private Integer empId;

    /**
     *
     */
    private String empName;

    /**
     *
     */
    private String empSex;

    /**
     *
     */
    private Integer empAge;

    /**
     *
     */
    private Integer deptId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Emp other = (Emp) that;
        return (this.getEmpId() == null ? other.getEmpId() == null : this.getEmpId().equals(other.getEmpId()))
            && (this.getEmpName() == null ? other.getEmpName() == null : this.getEmpName().equals(other.getEmpName()))
            && (this.getEmpSex() == null ? other.getEmpSex() == null : this.getEmpSex().equals(other.getEmpSex()))
            && (this.getEmpAge() == null ? other.getEmpAge() == null : this.getEmpAge().equals(other.getEmpAge()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEmpId() == null) ? 0 : getEmpId().hashCode());
        result = prime * result + ((getEmpName() == null) ? 0 : getEmpName().hashCode());
        result = prime * result + ((getEmpSex() == null) ? 0 : getEmpSex().hashCode());
        result = prime * result + ((getEmpAge() == null) ? 0 : getEmpAge().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", empId=").append(empId);
        sb.append(", empName=").append(empName);
        sb.append(", empSex=").append(empSex);
        sb.append(", empAge=").append(empAge);
        sb.append(", deptId=").append(deptId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
