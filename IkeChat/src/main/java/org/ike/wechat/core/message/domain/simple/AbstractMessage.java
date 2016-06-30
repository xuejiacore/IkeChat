/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message.domain.simple
 * Author: Xuejia
 * Date Time: 2016/6/30 18:19
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message.domain.simple;

/**
 * Class Name: AbstractMessage
 * Create Date: 2016/6/30 18:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public abstract class AbstractMessage implements IMessage {
    private String ToUserName = null;
    private String FromUserName = null;
    private Long CreateTime = null;
    private String MsgType = null;

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

    public IMessage reverse() {
        String user = ToUserName;
        ToUserName = FromUserName;
        FromUserName = user;
        CreateTime = System.currentTimeMillis();
        return this;
    }

    public String toXml() {
        return null;
    }
}
