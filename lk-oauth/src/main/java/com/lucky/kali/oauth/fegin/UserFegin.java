package com.lucky.kali.oauth.fegin;

import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.vo.req.LoginVO;
import com.lucky.kali.common.vo.resp.UserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Elliot
 */
@FeignClient(name = "lk-business", contextId = "lk-business-doLogin")
public interface UserFegin {

    /**
     * Fegin登陆接口
     *
     * @param loginVO 登陆信息
     * @return 查询结果
     */
    @PostMapping("userServer/doLogin")
    ResponseInfo<UserInfoVO> doLogin(LoginVO loginVO);
}
