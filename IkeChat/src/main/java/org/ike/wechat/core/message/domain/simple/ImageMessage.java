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
 * Class Name: ImageMessage
 * Create Date: 2016/6/30 7:43
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class ImageMessage implements IMessage {
    private String ToUserName = null;                       // 开发者微信号
    private String FromUserName = null;                     // 发送方帐号（一个OpenID）
    private Long CreateTime = null;                         // 消息创建时间 （整型）
    private String MsgType = null;                          // image
    private String PicUrl = null;                           // 图片链接（由系统生成）
    private String MediaId = null;                          // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private BigInteger MsgId = null;                        // 消息id，64位整型

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

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
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
