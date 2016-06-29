/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.statistics.bean
 * Author: Xuejia
 * Date Time: 2016/6/29 13:44
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.statistics.bean;

import com.google.gson.Gson;

/**
 * Class Name: InterfaceData
 * Create Date: 2016/6/29 13:44
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class InterfaceData {
    private String ref_date = null;                     // 数据的日期
    private Integer ref_hour = null;                    // 数据的小时
    private Integer callback_count = null;              // 通过服务器配置地址获得消息后，被动回复用户消息的次数
    private Integer fail_count = null;                  // 上述动作的失败次数
    private Integer total_time_cost = null;             // 总耗时，除以callback_count即为平均耗时
    private Integer max_time_cost = null;               // 最大耗时

    /**
     * @return 数据的日期
     */
    public String getRef_date() {
        return ref_date;
    }

    public void setRef_date(String ref_date) {
        this.ref_date = ref_date;
    }

    /**
     * @return 数据的小时
     */
    public Integer getRef_hour() {
        return ref_hour;
    }

    public void setRef_hour(Integer ref_hour) {
        this.ref_hour = ref_hour;
    }

    /**
     * @return 通过服务器配置地址获得消息后，被动回复用户消息的次数
     */
    public Integer getCallback_count() {
        return callback_count;
    }

    public void setCallback_count(Integer callback_count) {
        this.callback_count = callback_count;
    }

    /**
     * @return 上述动作的失败次数
     */
    public Integer getFail_count() {
        return fail_count;
    }

    public void setFail_count(Integer fail_count) {
        this.fail_count = fail_count;
    }

    /**
     * @return 总耗时，除以callback_count即为平均耗时
     */
    public Integer getTotal_time_cost() {
        return total_time_cost;
    }

    public void setTotal_time_cost(Integer total_time_cost) {
        this.total_time_cost = total_time_cost;
    }

    /**
     * @return 最大耗时
     */
    public Integer getMax_time_cost() {
        return max_time_cost;
    }

    public void setMax_time_cost(Integer max_time_cost) {
        this.max_time_cost = max_time_cost;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
