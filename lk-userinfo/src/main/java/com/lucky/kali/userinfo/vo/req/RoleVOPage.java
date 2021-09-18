package com.lucky.kali.userinfo.vo.req;

import io.swagger.annotations.ApiModel;
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
@ApiModel("角色表查询实体")
public class RoleVOPage implements Serializable {

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

    /**
     * 系统id
     */
    @ApiModelProperty(value = "系统id", name = "sysId", hidden = false, required = false, position = 3)
    private String sysId;

    /**
     * 角色代码
     */
    @ApiModelProperty(value = "角色代码", name = "code", hidden = true, required = false, position = 4)
    private String code;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", name = "name", hidden = false, required = false, position = 5)
    private String name;

    /**
     * 角色英文名称
     */
    @ApiModelProperty(value = "角色英文名称", name = "enName", hidden = false, required = false, position = 6)
    private String enName;

    /**
     * 角色类型
     */
    @ApiModelProperty(value = "角色类型", name = "type", hidden = false, required = false, position = 7)
    private String type;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", name = "status", hidden = false, required = false, position = 8)
    private String status;

    /**
     * 数据权限
     */
    @ApiModelProperty(value = "数据权限", name = "dataPermissionType", hidden = true, required = false, position = 9)
    private String dataPermissionType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remarks", hidden = true, required = false, position = 10)
    private String remarks;
}
