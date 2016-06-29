/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message
 * Author: Xuejia
 * Date Time: 2016/6/29 22:27
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message;

/**
 * Class Name: MsgType
 * Create Date: 2016/6/29 22:27
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public interface MsgType {
    /**
     * 文本消息
     */
    String MSG_TYPE_TEXT = "text";
    /**
     * 图片消息
     */
    String MSG_TYPE_IMAGE = "image";
    /**
     * 语音消息
     */
    String MSG_TYPE_VOICE = "voice";
    /**
     * 视频消息
     */
    String MSG_TYPE_VIDEO = "video";
    /**
     * 小视频消息
     */
    String MSG_TYPE_SHORTVIDEO = "shortvideo";
    /**
     * 地理位置消息
     */
    String MSG_TYPE_LOCATION = "location";
    /**
     * 链接消息
     */
    String MSG_TYPE_LINK = "link";
}
