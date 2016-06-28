/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.material.bean
 * Author: Xuejia
 * Date Time: 2016/6/28 13:02
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.material.bean;

import com.google.gson.Gson;

/**
 * Class Name: MaterialList
 * Create Date: 2016/6/28 13:02
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class MaterialList {
    private Integer total_count = null;         // 该类型的素材的总数
    private Integer item_count = null;          // 本次调用获取的素材的数量
    private Item item = null;                   // 素材列表

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Integer getItem_count() {
        return item_count;
    }

    public void setItem_count(Integer item_count) {
        this.item_count = item_count;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
