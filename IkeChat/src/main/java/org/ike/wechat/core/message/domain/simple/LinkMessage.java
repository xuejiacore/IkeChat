/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message
 * Author: Xuejia
 * Date Time: 2016/6/30 7:43
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message.domain.simple;

import com.google.gson.Gson;

import java.math.BigInteger;

/**
 * Class Name: LinkMessage
 * Create Date: 2016/6/30 7:43
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:链接消息
 */
public class LinkMessage implements IMessage {
    private String ToUserName = null;
    private String FromUserName = null;
    private Long CreateTime = null;
    private String MsgType = null;
    private String Title = null;
    private String Description = null;
    private String Url = null;
    private BigInteger MsgId = null;

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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public BigInteger getMsgId() {
        return MsgId;
    }

    public void setMsgId(BigInteger msgId) {
        MsgId = msgId;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
