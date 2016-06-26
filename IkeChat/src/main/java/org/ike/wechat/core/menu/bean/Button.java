/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.menu.bean
 * Author: Xuejia
 * Date Time: 2016/6/26 15:18
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.menu.bean;

import org.apache.log4j.Logger;
import org.ike.wechat.core.IkeChat;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name: Button
 * Create Date: 2016/6/26 15:18
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 菜单按钮类
 */
public class Button {
    private String type;
    private String name;
    private String key;
    private String url;
    private String media_id;
    private List<Button> sub_button;
    private boolean isSub = false;

    public void addSubButton(Button button) {
        if (sub_button == null) {
            sub_button = new ArrayList<Button>();
        }
        button.isSub = true;
        if (!this.isSub) {
            sub_button.add(button);
        } else {
            Logger.getLogger(IkeChat.LOGGER_NAME).warn("子菜单添加失败，不允许向二级菜单中添加子菜单:" + button.toString());
        }
    }

    public Button() {
        this.sub_button = new ArrayList<Button>();
    }

    public Button(String name) {
        this.name = name;
    }

    public Button(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public Button(String type, String name, String key) {
        this.type = type;
        this.name = name;
        this.key = key;
    }

    public Button(String type, String name, String key, String url) {
        this.type = type;
        this.name = name;
        this.key = key;
        this.url = url;
    }

    public Button(String type, String name, String key, String url, String media_id) {
        this.type = type;
        this.name = name;
        this.key = key;
        this.url = url;
        this.media_id = media_id;
    }

    public Button(String type, String name, String key, String url, String media_id, List<Button> sub_button) {
        this.type = type;
        this.name = name;
        this.key = key;
        this.url = url;
        this.media_id = media_id;
        this.sub_button = sub_button;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public List<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Button> sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return "Button{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", media_id='" + media_id + '\'' +
                ", sub_button=" + sub_button +
                ", isSub=" + isSub +
                '}';
    }
}
