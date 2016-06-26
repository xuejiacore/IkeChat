/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.menu.bean
 * Author: Xuejia
 * Date Time: 2016/6/26 17:29
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.menu.bean;

/**
 * Class Name: MenuInfo
 * Create Date: 2016/6/26 17:29
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class MenuInfo {
    private Menu menu = null;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "MenuInfo{" +
                "menu=" + menu +
                '}';
    }
}
