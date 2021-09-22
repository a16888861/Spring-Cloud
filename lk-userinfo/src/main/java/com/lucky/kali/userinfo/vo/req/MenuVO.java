package com.lucky.kali.userinfo.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 菜单信息VO
 *
 * @author Elliot
 */
@Data
@NoArgsConstructor
@ApiModel("菜单表实体")
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", name = "id", hidden = true, required = false)
    private String id;

    /**
     * 系统id
     */
    @ApiModelProperty(value = "系统id", name = "sysId", hidden = false, required = true, position = 0)
    @NotEmpty(message = "menu.sysId.NotEmpty")
    private String sysId;

    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id", name = "parentId", hidden = false, required = false, position = 1)
    private String parentId;

    /**
     * 父级code
     */
    @ApiModelProperty(value = "父级code", name = "parentCode", hidden = false, required = false, position = 2)
    private String parentCode;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", name = "name", hidden = false, required = true, position = 3)
    @NotEmpty(message = "menu.sysName.NotEmpty")
    private String name;

    /**
     * 菜单访问路径
     */
    @ApiModelProperty(value = "菜单访问路径", name = "href", hidden = false, required = true, position = 4)
    @NotEmpty(message = "menu.sysHref.NotEmpty")
    private String href;

    /**
     * 菜单打开方式
     */
    @ApiModelProperty(value = "菜单打开方式", name = "target", hidden = false, required = false, position = 5)
    private String target;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标", name = "icon", hidden = false, required = false, position = 6)
    private String icon;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "sort", hidden = false, required = true, position = 7)
    @NotEmpty(message = "menu.sysSort.NotEmpty")
    private String sort;

    /**
     * 菜单编码(从000000开始)
     */
    @ApiModelProperty(value = "菜单编码(从000000开始)", name = "code", hidden = false, required = true, position = 8)
    @NotEmpty(message = "menu.sysCode.NotEmpty")
    private String code;

    /**
     * 菜单状态(0锁定1正常)
     */
    @ApiModelProperty(value = "菜单状态(lock锁定unLock正常)", name = "status", hidden = false, required = true, position = 9)
    @NotEmpty(message = "menu.sysStatus.NotEmpty")
    private String status;

    /**
     * 是否显示(传show或notShow 0否1是)
     */
    @ApiModelProperty(value = "是否显示(传show或notShow)", name = "isShow", hidden = false, required = true, position = 10)
    @NotEmpty(message = "menu.sysIsShow.NotEmpty")
    private String isShow;

    /**
     * 菜单类型(0总菜单，子菜单用1，2，3，4，5顺序向下区分)
     */
    @ApiModelProperty(value = "菜单类型(0总菜单，子菜单用1，2，3，4，5顺序向下区分)", name = "type", hidden = false, required = false, position = 11)
    private String type;
}
