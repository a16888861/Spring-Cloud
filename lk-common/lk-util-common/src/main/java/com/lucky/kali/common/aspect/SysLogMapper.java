package com.lucky.kali.common.aspect;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志表mapper
 *
 * @author Elliot
 * @date 2021/09/19 11:00 下午
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogEntity> {
}
