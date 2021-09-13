package com.lucky.kali.userinfo.dto;

import com.lucky.kali.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 系统-组别角色表
 *
 * @author Elliot
 * @since 2021-08-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class GroupRoleDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组别id
     */
    private String groupId;

    /**
     * 角色id
     */
    private String roleId;

}
