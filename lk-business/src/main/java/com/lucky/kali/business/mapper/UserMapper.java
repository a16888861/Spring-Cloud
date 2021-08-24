package com.lucky.kali.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.kali.business.entity.User;
import org.springframework.context.annotation.Primary;

/**
 * 用户表 Mapper 接口
 *
 * @author Elliot
 * @since 2021-07-18
 */
@Primary
public interface UserMapper extends BaseMapper<User> {

}
