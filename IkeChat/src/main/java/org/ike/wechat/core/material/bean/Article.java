/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.material.bean
 * Author: Xuejia
 * Date Time: 2016/6/28 8:19
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.material.bean;

import com.google.gson.Gson;

/**
 * Class Name: Article
 * Create Date: 2016/6/28 8:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 图文素材文章
 */
public class Article {
    private String title = null;                        // 标题
    private String thumb_media_id = null;               // 图文消息的封面图片素材id（必须是永久mediaID）
    private String author = null;                       // 作者
    private String digest = null;                       // 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
    private Integer show_cover_pic = null;              // 是否显示封面，0为false，即不显示，1为true，即显示
    private String content = null;                      // 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
    private String content_source_url = null;           // 图文消息的原文地址，即点击“阅读原文”后的URL

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
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

    public Integer getShow_cover_pic() {
        return show_cover_pic;
    }

    public void setShow_cover_pic(Integer show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_source_url() {
        return content_source_url;
    }

    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
