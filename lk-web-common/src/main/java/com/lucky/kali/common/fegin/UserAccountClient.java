package com.lucky.kali.common.fegin;

import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.vo.req.LoginVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Elliot
 */
@FeignClient(name = "lk-business", contextId = "userAccount")
public interface UserAccountClient {

    /**
     * 调用登陆
     *
     * @param loginVO 登陆信息
     * @return 返回的结果
     */
    @PostMapping("business/user/doLogin")
    ResponseInfo<String> doLogin(@RequestBody LoginVO loginVO);
}