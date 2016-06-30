/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message
 * Author: Xuejia
 * Date Time: 2016/6/29 23:29
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message.domain.simple;

import com.google.gson.Gson;
import org.ike.wechat.core.message.BaseMessage;

import java.math.BigInteger;

/**
 * Class Name: TextMessage
 * Create Date: 2016/6/29 23:29
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class TextMessage implements IMessage {
    private String ToUserName = null;
    private String FromUserName = null;
    private Long CreateTime = null;
    private String MsgType = null;
    private String Content = null;                          // 文本消息内容
    private BigInteger MsgId = null;                        // 消息id，64位整型
    private String URL = null;

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

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
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
