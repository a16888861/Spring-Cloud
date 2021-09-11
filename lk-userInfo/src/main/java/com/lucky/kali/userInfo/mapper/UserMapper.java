package com.lucky.kali.userInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.kali.userInfo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表 Mapper 接口
 *
 * @author Elliot
 * @since 2021-07-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
