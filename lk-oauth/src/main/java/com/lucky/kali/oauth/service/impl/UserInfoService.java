package com.lucky.kali.oauth.service.impl;

import com.lucky.kali.common.dto.UserDTO;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.vo.req.LoginVO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Elliot
 */
public interface UserInfoService extends UserDetailsService {

    /**
     * 用户登录接口
     *
     * @param loginVO 登录信息
     * @return 登录结果
     */
    ResponseInfo<UserDTO> loadUserByUsername(LoginVO loginVO);
}
