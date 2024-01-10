package com.example.demo.mapper;

import com.example.demo.bean.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Zqunnnnnn
* @description 针对表【sys_role_menu】的数据库操作Mapper
* @createDate 2024-01-02 16:42:20
* @Entity com.example.demo.bean.SysRoleMenu
*/
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    @Select("SELECT menu_id FROM `sys_role_menu` where role_id = #{roleID} ")
    List<Integer> selectMenusByRoleId(Integer roleID);
}




