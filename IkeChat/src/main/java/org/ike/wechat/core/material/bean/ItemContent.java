/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.material.bean
 * Author: Xuejia
 * Date Time: 2016/6/28 12:56
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.material.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Class Name: ItemContent
 * Create Date: 2016/6/28 12:56
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class ItemContent {
    private List<Article> news_item = null;     // 图文素材列表

    public List<Article> getNews_item() {
        return news_item;
    }

    public void setNews_item(List<Article> news_item) {
        this.news_item = news_item;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
