/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message.listener
 * Author: Xuejia
 * Date Time: 2016/6/30 14:04
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message;

/**
 * Class Name: EventType
 * Create Date: 2016/6/30 14:04
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public interface EventType {
    /**
     * 关注事件
     */
    String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /**
     * 取消关注事件
     */
    String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    /**
     * 扫码事件
     */
    String EVENT_TYPE_SCAN = "scan";
    /**
     * 发送位置信息事件
     */
    String EVENT_TYPE_LOCATION = "location";
    /**
     * 点击菜单拉取信息事件
     */
    String EVENT_TYPE_CLICK = "click";
    /**
     * 点击菜单跳转时间
     */
    String EVENT_TYPE_VIEW = "view";
}
