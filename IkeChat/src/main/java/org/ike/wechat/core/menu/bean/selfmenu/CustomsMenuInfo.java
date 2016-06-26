/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.menu.bean.selfmenu
 * Author: Xuejia
 * Date Time: 2016/6/26 15:23
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.menu.bean.selfmenu;

import com.google.gson.Gson;

/**
 * Class Name: CustomsMenuInfo
 * Create Date: 2016/6/26 15:23
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 查询整个自定义菜单返回的结果
 */
public class CustomsMenuInfo {
    private Integer is_menu_open;               // 菜单是否开启，0代表未开启，1代表开启
    private SelfMenuInfo selfmenu_info;         // 菜单信息

    public Integer getIs_menu_open() {
        return is_menu_open;
    }

    public void setIs_menu_open(Integer is_menu_open) {
        this.is_menu_open = is_menu_open;
    }

    public SelfMenuInfo getSelfmenu_info() {
        return selfmenu_info;
    }

    public void setSelfmenu_info(SelfMenuInfo selfmenu_info) {
        this.selfmenu_info = selfmenu_info;
    }

    /**
     * 判断当前菜单是否开启
     *
     * @return 如果开启中，那么返回值为true，否则返回值为false
     */
    public boolean isMenuOpen() {
        return is_menu_open == 1;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
