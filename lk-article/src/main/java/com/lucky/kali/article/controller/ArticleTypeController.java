package com.lucky.kali.article.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Elliot
 * @date 2021/09/19 8:11 下午
 */
@Slf4j
@RestController("article/articleType")
@Api(value = "文章分类接口", tags = "文章分类接口")
@ApiSupport(order = 101, author = "Elliot")
public class ArticleTypeController {

    @PostMapping("selectArticleType")
    @ApiOperation(value = "查询文章分类分页信息", produces = "application/json")
    public void selectArticleType() {

    }
}
