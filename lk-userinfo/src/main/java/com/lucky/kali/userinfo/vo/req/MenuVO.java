package com.lucky.kali.userinfo.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 菜单信息VO
 *
 * @author Elliot
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@ApiModel("菜单表实体")
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统id
     */
    @ApiModelProperty(value = "系统id", name = "sysId", hidden = false, required = true)
    @NotEmpty(message = "menu.sysId.isEmpty")
    private String sysId;

    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id", name = "parentId", hidden = false, required = false)
    private String parentId;

    /**
     * 父级code
     */
    @ApiModelProperty(value = "父级code", name = "parentCode", hidden = false, required = false)
    private String parentCode;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", name = "name", hidden = false, required = true)
    private String name;

    /**
     * 菜单访问路径
     */
    @ApiModelProperty(value = "菜单访问路径", name = "href", hidden = false, required = true)
    private String href;

    /**
     * 菜单打开方式
     */
    @ApiModelProperty(value = "菜单打开方式", name = "target", hidden = false, required = true)
    private String target;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标", name = "icon", hidden = false, required = true)
    private String icon;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "sort", hidden = false, required = true)
    private String sort;

    /**
     * 菜单编码
     */
    @ApiModelProperty(value = "菜单编码", name = "code", hidden = false, required = true)
    private String code;

    /**
     * 菜单状态
     */
    @ApiModelProperty(value = "菜单状态", name = "status", hidden = false, required = true)
    private String status;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示", name = "isShow", hidden = false, required = true)
    private String isShow;

    /**
     * 菜单类型
     */
    @ApiModelProperty(value = "菜单类型", name = "type", hidden = false, required = true)
    private String type;
}
