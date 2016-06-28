/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.material.bean
 * Author: Xuejia
 * Date Time: 2016/6/28 13:01
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.material.bean;

import com.google.gson.Gson;

/**
 * Class Name: MediaInfo
 * Create Date: 2016/6/28 13:01
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class MediaInfo {
    private String media_id = null;                 // 素材ID
    private ItemContent content = null;             // 图文素材体
    private Long update_time = null;                // 更新时间
    private String name = null;                     // 素材名称
    private String url = null;                      // 素材的URL

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public ItemContent getContent() {
        return content;
    }

    public void setContent(ItemContent content) {
        this.content = content;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
