package com.example.demo.mapper;

import com.example.demo.bean.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zqu
 * @since 2023-12-28
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select id from role where flag=#{role}")
    Integer selectByFlag(String role);
}
