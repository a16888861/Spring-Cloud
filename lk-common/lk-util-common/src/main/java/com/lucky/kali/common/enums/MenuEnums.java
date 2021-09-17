package com.lucky.kali.common.enums;

/**
 * 菜单信息枚举类
 *
 * @author Elliot
 */
public enum MenuEnums {

    /**
     * 菜单锁定
     */
    MENU_LOCK("0", "lock"),
    /**
     * 菜单未锁定
     */
    MENU_UNLOCK("1", "unLock"),
    /**
     * 菜单不展示
     */
    MENU_NOT_SHOW("0", "notShow"),
    /**
     * 菜单展示
     */
    MENU_SHOW("1", "show");

    /**
     * 菜单Code
     */
    private final String menuCode;
    /**
     * 菜单Name
     */
    private final String menuName;

    MenuEnums(String menuCode, String menuName) {
        this.menuCode = menuCode;
        this.menuName = menuName;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    /**
     * 获取菜单Name
     *
     * @param menuCode 菜单Code
     * @return 菜单Name
     */
    public static String getMenuName(String menuCode) {
        for (MenuEnums g : MenuEnums.values()) {
            if (g.getMenuCode().equals(menuCode)) {
                return g.menuName;
            }
        }
        return null;
    }

    /**
     * 获取菜单Code
     *
     * @param menuName 菜单Name
     * @return 菜单Code
     */
    public static String getMenuCode(String menuName) {
        for (MenuEnums g : MenuEnums.values()) {
            if (g.getMenuName().equals(menuName)) {
                return g.menuCode;
            }
        }
        return null;
    }
}
