package com.lucky.kali.userinfo.controller;


import cn.hutool.core.util.ObjectUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lucky.kali.common.enums.MenuEnums;
import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.util.PageUtil;
import com.lucky.kali.userinfo.dto.MenuDTO;
import com.lucky.kali.userinfo.service.MenuService;
import com.lucky.kali.userinfo.vo.req.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统-菜单表 前端控制器
 *
 * @author Elliot
 * @since 2021-08-24
 */
@RestController("menuController")
@RequestMapping("/userInfo/menu")
@Api(value = "菜单信息", tags = "菜单信息接口")
@ApiSupport(order = 105, author = "Elliot")
public class MenuController {
    @Resource
    private MenuService menuService;

    /**
     * 创建菜单
     *
     * @param menuVO        菜单信息实体
     * @param bindingResult 参数校验
     * @return 创建结果
     */
    @PostMapping("createMenu")
    @ApiOperation(value = "创建菜单信息接口", produces = "application/json",
            notes = "一级菜单归属于顶级菜单，创建一级菜单使用以下默认信息<br>" +
                    "顶级菜单默认id为1438882770994839552<br>" +
                    "顶级菜单默认code为000000")
    public ResponseInfo<Response> createMenu(@Valid @RequestBody MenuVO menuVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.fail(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Integer result = menuService.createMenu(menuVO);
        if (result > 0) {
            return Response.success(ResponseEnum.SUCCESS.getMessage());
        } else {
            return Response.fail(ResponseEnum.FAILURE.getMessage());
        }
    }

    /**
     * 查询菜单集合(用于给角色分配权限时用)
     * 传'top'查询顶级菜单信息
     * 传'null'默认查询一级菜单集合
     * 传父级id则根据父级ID查询子级菜单集合
     *
     * @param parentId 查询参数
     * @return 查询结果
     */
    @GetMapping("selectMenuList/{parentId}")
    @ApiOperation(value = "查询菜单集合接口", produces = "application/json",
            notes = "用于给角色分配权限时用<br>" +
                    "传'top'查询顶级菜单信息<br>" +
                    "传'null'默认查询一级菜单集合<br>" +
                    "传父级id则根据父级ID查询子级菜单集合")
    public ResponseInfo<List<MenuVO>> selectMenuList(@ApiParam(value = "父级id", required = false) @PathVariable("parentId") String parentId) {
        List<MenuDTO> menuDTOList = menuService.selectMenuList(parentId);
        if (menuDTOList.isEmpty() || ObjectUtil.isNull(menuDTOList)) {
            return Response.notFound(ResponseEnum.NOTFOUND.getMessage());
        }
        /*处理返回信息*/
        List<MenuDTO> collect = menuDTOList.stream().map(this::replaceColumns).collect(Collectors.toList());
        List<MenuVO> menuVoList = PageUtil.copyList(collect, MenuVO.class);
        return Response.success(ResponseEnum.SUCCESS.getMessage(), menuVoList);
    }

    /**
     * 处理集合的返回字段
     *
     * @param menuDTO 菜单dto
     * @return 处理结果
     */
    private MenuDTO replaceColumns(MenuDTO menuDTO) {
        menuDTO.setIsShow(menuDTO.getIsShow().equals(MenuEnums.MENU_SHOW.getMenuCode()) ? MenuEnums.MENU_SHOW.getMenuName() : MenuEnums.MENU_NOT_SHOW.getMenuName())
                .setStatus(menuDTO.getStatus().equals(MenuEnums.MENU_UNLOCK.getMenuCode()) ? MenuEnums.MENU_UNLOCK.getMenuName() : MenuEnums.MENU_LOCK.getMenuName());
        return menuDTO;
    }
}
