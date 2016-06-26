/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.menu.bean.selfmenu
 * Author: Xuejia
 * Date Time: 2016/6/26 15:22
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.menu.bean.selfmenu;

import com.google.gson.Gson;

/**
 * Class Name: News
 * Create Date: 2016/6/26 15:22
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 菜单接口中的图文实体
 */
public class News {
    private String title = null;            // 图文消息的标题
    private String author = null;           // 作者
    private String digest = null;           // 摘要
    private String show_cover = null;       // 是否显示封面，0为不显示，1为显示
    private String cover_url = null;        // 封面图片的URL
    private String content_url = null;      // 正文的URL
    private String source_url = null;       // 原文的URL，若置空则无查看原文入口

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getShow_cover() {
        return show_cover;
    }

    public void setShow_cover(String show_cover) {
        this.show_cover = show_cover;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
