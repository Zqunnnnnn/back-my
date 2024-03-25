package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * <p>
 *
 * </p>
 *
 * @author Zqu
 * @since 2023-12-28
 */
@Getter
@Setter
@TableName("role")
@ApiModel(value = "Role对象", description = "")
@CrossOrigin
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String description;
    private String flag;
}
