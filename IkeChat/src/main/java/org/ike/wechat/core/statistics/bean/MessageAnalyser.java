/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.statistics.bean
 * Author: Xuejia
 * Date Time: 2016/6/29 13:40
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.statistics.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Class Name: MessageAnalyser
 * Create Date: 2016/6/29 13:40
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class MessageAnalyser {
    private List<MessageData> list = null;

    public List<MessageData> getList() {
        return list;
    }

    public void setList(List<MessageData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
