/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.material.bean
 * Author: Xuejia
 * Date Time: 2016/6/28 12:58
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.material.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Class Name: Item
 * Create Date: 2016/6/28 12:58
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class Item {
    private List<MediaInfo> item = null;        // 图文消息列表项

    public List<MediaInfo> getItem() {
        return item;
    }

    public void setItem(List<MediaInfo> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
