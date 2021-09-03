package com.lucky.kali.business.dto;

import com.lucky.kali.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 用户表DTO
 *
 * @author Elliot
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserDTO extends BaseDTO implements Serializable {

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
     * 角色ID
     */
    private String roleId;

    /**
     * 年份
     */
    private String year;

    /**
     * 状态(0正常,1锁定)
     */
    private String status;

}
