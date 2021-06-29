package com.lucky.kali.business.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Elliot
 * @date 2021-06-02 21:55
 */
@RestController
@Api(value = "首页信息",tags = "首页信息接口")
@ApiSupport(order = 100, author = "Elliot")
@RequestMapping("index")
public class IndexController extends BaseController {

}
