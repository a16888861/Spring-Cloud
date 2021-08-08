package com.lucky.kali.consumer.dto;

import com.lucky.kali.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
/**
 * 用户表DTO
 *
 * @author Elliot
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户名-别名
     */
    private String screenName;

    /**
     * 用户组别
     */
    private String userGroup;

    /**
     * 年份
     */
    private String year;

}
