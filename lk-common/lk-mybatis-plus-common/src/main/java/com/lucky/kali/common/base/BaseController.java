package com.lucky.kali.common.base;

import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;

/**
 * 通用Controller
 *
 * @author Elliot
 * @date 2021/09/20 4:29 下午
 */
public class BaseController {

    /**
     * 通用判断操作结果(用于新增修改或删除操作)
     *
     * @param result 操作结果
     * @return 返回信息
     */
    public ResponseInfo<Response> judgeResult(int result) {
        if (result > 0) {
            return Response.success(ResponseEnum.SUCCESS.getMessage());
        } else {
            return Response.fail(ResponseEnum.FAILURE.getMessage());
        }
    }
}
