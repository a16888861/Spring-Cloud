package com.lucky.kali.business.dto;

import com.lucky.kali.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
/**
 * 系统-角色表
 *
 * @author Elliot
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class RoleDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 系统id
     */
    private String sysId;

    /**
     * 角色代码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色英文名称
     */
    private String enName;

    /**
     * 角色类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 数据权限
     */
    private String dataPermissionType;

    /**
     * 备注
     */
    private String remarks;
}
