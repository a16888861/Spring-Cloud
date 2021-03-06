package com.lucky.kali.userinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.kali.userinfo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统-菜单表 Mapper 接口
 *
 * @author Elliot
 * @since 2021-08-24
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
