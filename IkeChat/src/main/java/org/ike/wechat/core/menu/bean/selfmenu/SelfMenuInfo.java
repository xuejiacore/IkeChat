/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.menu.bean.selfmenu
 * Author: Xuejia
 * Date Time: 2016/6/26 15:23
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.menu.bean.selfmenu;

import com.google.gson.Gson;

import java.util.List;

/**
 * Class Name: SelfMenuInfo
 * Create Date: 2016/6/26 15:23
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 菜单信息实体类
 */
public class SelfMenuInfo {

    private List<SelfButton> button;        // 自定义按钮组

    public List<SelfButton> getSelfButton() {
        return button;
    }

    public void setSelfButton(List<SelfButton> selfButton) {
        this.button = selfButton;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
