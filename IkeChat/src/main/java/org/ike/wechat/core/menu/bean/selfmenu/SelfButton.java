/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.menu.bean.selfmenu
 * Author: Xuejia
 * Date Time: 2016/6/26 15:21
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.menu.bean.selfmenu;

import com.google.gson.Gson;

import java.util.List;

/**
 * Class Name: SelfButton
 * Create Date: 2016/6/26 15:21
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 图文消息中的菜单按钮
 */
public class SelfButton {
    private String name;
    private String type;
    private String key;
    private String url;
    private String value;
    private NewsInfo news_info;
    private SelfButton sub_button;
    private List<SelfButton> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public NewsInfo getNews_info() {
        return news_info;
    }

    public void setNews_info(NewsInfo news_info) {
        this.news_info = news_info;
    }

    public SelfButton getSub_button() {
        return sub_button;
    }

    public void setSub_button(SelfButton sub_button) {
        this.sub_button = sub_button;
    }

    public List<SelfButton> getList() {
        return list;
    }

    public void setList(List<SelfButton> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
