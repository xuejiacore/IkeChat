/**
 * Project: IkeChat
 * Package Name: basic.test
 * Author: Xuejia
 * Date Time: 2016/6/29 21:23
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package basic.test;

/**
 * Class Name: MessageTest
 * Create Date: 2016/6/29 21:23
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class MessageTest {
    private String ToUserName = null;
    private String FromUserName = null;
    private Long CreateTime = null;
    private String MsgType = null;
    private String Content = null;
    private String MsgId = null;
    private String Demo = null;

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

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public String getDemo() {
        return Demo;
    }

    public void setDemo(String demo) {
        Demo = demo;
    }

    @Override
    public String toString() {
        return "MessageTest{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                ", Content='" + Content + '\'' +
                ", MsgId='" + MsgId + '\'' +
                ", Demo='" + Demo + '\'' +
                '}';
    }
}
