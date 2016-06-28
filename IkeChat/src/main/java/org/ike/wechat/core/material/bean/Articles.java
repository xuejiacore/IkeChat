/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.material.bean
 * Author: Xuejia
 * Date Time: 2016/6/28 8:19
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.material.bean;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name: Articles
 * Create Date: 2016/6/28 8:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 文章列表
 */
public class Articles {

    private String media_id = null;             // 要修改的图文消息的id
    private Integer index = null;               // 要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
    private List<Article> articles = null;      // 文章列表

    public Articles() {
        this.articles = new ArrayList<Article>();
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    /**
     * 添加一个图文素材到多图文素材列表中
     *
     * @param article 需要添加的图文素材
     */
    public void addArticle(Article article) {
        this.articles.add(article);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
