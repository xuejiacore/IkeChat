/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.menu.bean.selfmenu
 * Author: Xuejia
 * Date Time: 2016/6/26 15:22
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.menu.bean.selfmenu;

import com.google.gson.Gson;

import java.util.List;

/**
 * Class Name: NewsInfo
 * Create Date: 2016/6/26 15:22
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 菜单接口中的图文列表
 */
public class NewsInfo {
    private List<News> list;    // 图文列表

    public List<News> getList() {
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
