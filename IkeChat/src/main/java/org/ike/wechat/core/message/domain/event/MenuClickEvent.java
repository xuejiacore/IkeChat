/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message.domain.event
 * Author: Xuejia
 * Date Time: 2016/6/30 13:37
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message.domain.event;

import com.google.gson.Gson;
import org.ike.wechat.core.message.domain.simple.IMessage;

/**
 * Class Name: MenuClickEvent
 * Create Date: 2016/6/30 13:37
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class MenuClickEvent implements IMessage {
    private String ToUserName = null;
    private String FromUserName = null;
    private Long CreateTime = null;
    private String MsgType = null;
    private String Event = null;
    private String EventKey = null;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
