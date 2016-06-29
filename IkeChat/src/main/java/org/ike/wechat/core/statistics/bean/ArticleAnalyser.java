/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.statistics.bean
 * Author: Xuejia
 * Date Time: 2016/6/29 13:12
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.statistics.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Class Name: ArticleAnalyser
 * Create Date: 2016/6/29 13:12
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class ArticleAnalyser {
    private List<ArticleData> list = null;      // 分析列表

    public List<ArticleData> getList() {
        return list;
    }

    public void setList(List<ArticleData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
