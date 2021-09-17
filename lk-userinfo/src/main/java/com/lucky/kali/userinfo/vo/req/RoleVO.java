package com.lucky.kali.userinfo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 角色表VO
 *
 * @author Elliot
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组别id
     */
    @ApiModelProperty(value = "组别id", name = "groupId", hidden = false, required = true, position = 0)
    @NotEmpty(message = "role.code.NotEmpty")
    private String groupId;

    /**
     * 系统id
     */
    @ApiModelProperty(value = "系统id", name = "sysId", hidden = true, required = false, position = 1)
    private String sysId;

    /**
     * 角色代码
     */
    @ApiModelProperty(value = "角色代码", name = "code", hidden = false, required = true, position = 2)
    @NotEmpty(message = "role.code.NotEmpty")
    private String code;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", name = "name", hidden = false, required = true, position = 3)
    @NotEmpty(message = "role.name.NotEmpty")
    private String name;

    /**
     * 角色英文名称
     */
    @ApiModelProperty(value = "角色英文名称", name = "enName", hidden = false, required = true, position = 4)
    @NotEmpty(message = "role.enName.NotEmpty")
    private String enName;

    /**
     * 角色类型
     */
    @ApiModelProperty(value = "角色类型", name = "type", hidden = false, required = true, position = 5)
    @NotEmpty(message = "role.type.NotEmpty")
    private String type;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", name = "status", hidden = false, required = true, position = 6)
    @NotEmpty(message = "role.status.NotEmpty")
    private String status;

    /**
     * 数据权限
     */
    @ApiModelProperty(value = "数据权限", name = "dataPermissionType", hidden = true, required = false, position = 7)
    private String dataPermissionType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remarks", hidden = false, required = false, position = 8)
    private String remarks;
}
