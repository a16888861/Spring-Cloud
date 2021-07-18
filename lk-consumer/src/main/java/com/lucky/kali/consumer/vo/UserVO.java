package com.lucky.kali.consumer.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserVO {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "name")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "mail")
    private String mail;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "phone")
    private String phone;

    /**
     * 用户名-别名
     */
    @ApiModelProperty(value = "用户名-别名", name = "screenname")
    private String screenName;

    /**
     * 用户组别
     */
    @ApiModelProperty(value = "用户组别", name = "group")
    private String group;
}
