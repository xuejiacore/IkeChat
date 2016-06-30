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
public class TextMessage extends AbstractMessage implements IMessage {

    private String Content = null;
    private BigInteger MsgId = null;
    private String URL = null;

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
