package com.lucky.kali.userinfo.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户表VO
 *
 * @author Elliot
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@ApiModel("用户表查询实体")
public class UserVOPage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", name = "pageCurrent", required = true, position = 1)
    @NotEmpty(message = "common.response.pageCurrent.NotEmpty")
    private Integer pageCurrent;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", name = "pageSize", required = true, position = 2)
    @NotEmpty(message = "common.response.pageSize.NotEmpty")
    private Integer pageSize;

    @ApiModelProperty(value = "用户标识符", name = "id", required = false, position = 3)
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "name", required = false, position = 4)
    private String name;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "mail", required = false, position = 5)
    private String mail;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "phone", required = false, position = 6)
    private String phone;

    /**
     * 用户名-别名
     */
    @ApiModelProperty(value = "用户名-别名", name = "screenName", required = false, position = 7)
    private String screenName;

    /**
     * 用户组别
     */
    @ApiModelProperty(value = "用户组别", name = "group", required = false, position = 8)
    private String userGroup;

    /**
     * 年份
     */
    @ApiModelProperty(value = "年份", name = "year", required = false, position = 9)
    private String year;

    /**
     * 状态(0正常,1锁定)
     */
    @ApiModelProperty(value = "状态(0正常,1锁定)", name = "status", required = false, position = 10)
    private String status;
}