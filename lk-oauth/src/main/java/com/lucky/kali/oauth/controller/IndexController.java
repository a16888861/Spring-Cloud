package com.lucky.kali.oauth.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elliot
 * @date 2021-06-02 21:55
 */
@RestController
@Api(value = "首页信息", tags = "首页信息接口")
@ApiSupport(order = 100, author = "Elliot")
public class IndexController {
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * buildName
     */
    @Value("${info.build.name}")
    private String buildName;
    /**
     * 获取Id
     */
    @Value("${spring.application.name}")
    private String applicationId;

    /**
     * Index信息
     *
     * @return Index信息
     */
    @GetMapping("/")
    @ApiOperation(value = "获取首页信息", produces = "application/json")
    @ApiOperationSupport(author = "Elliot")
    public ResponseInfo<List<String>> index() {
        List<String> result = new ArrayList<>();
        result.add("Welcome To The " + buildName + " ~");
        result.add("Priority of services : " + discoveryClient.getOrder());
        result.add("Service Id : " + applicationId);
        return Response.success(ResponseEnum.SUCCESS.getMessage(), result);
    }
}