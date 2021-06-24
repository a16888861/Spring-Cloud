package com.lucky.kali.business.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Elliot
 * @date 2021-06-01 13:31
 */
@RestController
@RequestMapping("business/info")
@Slf4j
@Api(value = "描述信息",tags = "描述信息接口")
@ApiSupport(order = 101, author = "Elliot")
public class InfoController {

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 获取description
     * @return  description
     */
    @GetMapping("getDescription")
    @ApiOperation(value = "获取description", produces = "application/json")
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<String> description(){
        return Response.success("common.response.success",discoveryClient.description());
    }
}
