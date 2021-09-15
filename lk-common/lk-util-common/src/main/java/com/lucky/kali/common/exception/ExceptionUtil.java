package com.lucky.kali.common.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Elliot
 */
@Slf4j
@Component
public final class ExceptionUtil {

    public static ResponseInfo<String> handleException(BlockException ex) {
        log.error("错误发生: " + ex.getClass().getCanonicalName());
        return Response.custom(445, "common.response.sentinel.error", null);
    }

}