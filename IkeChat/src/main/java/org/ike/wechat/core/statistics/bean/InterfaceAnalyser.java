/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.statistics.bean
 * Author: Xuejia
 * Date Time: 2016/6/29 13:44
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.statistics.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Class Name: InterfaceAnalyser
 * Create Date: 2016/6/29 13:44
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class InterfaceAnalyser {
    private List<InterfaceData> list = null;

    public List<InterfaceData> getList() {
        return list;
    }

    public void setList(List<InterfaceData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
