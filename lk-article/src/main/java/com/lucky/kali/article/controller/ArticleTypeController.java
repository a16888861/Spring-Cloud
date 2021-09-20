package com.lucky.kali.article.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.aspect.Log;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章分类接口
 *
 * @author Elliot
 * @date 2021/09/19 8:11 下午
 */
@Slf4j
@RestController("articleTypeController")
@RequestMapping("article/articleType")
@Api(value = "文章分类接口", tags = "文章分类接口")
@ApiSupport(order = 101, author = "Elliot")
public class ArticleTypeController {

    /**
     * 查询文章分类分页信息
     * @param userId    用户ID
     * @return  查询结果
     */
    @Log("查询文章分类分页信息")
    @GetMapping("selectArticleType/{userId}")
    @ApiOperation(value = "查询文章分类分页信息", produces = "application/json",
            notes = "如存在用户id，则查询当前用户自己创建的分类信息，否则查询公共分类")
    public ResponseInfo<List<String>> selectArticleType(@ApiParam(value = "用户id", required = false) @PathVariable("userId") String userId) {
        return Response.success(ResponseEnum.SUCCESS.getMessage());
    }
}
