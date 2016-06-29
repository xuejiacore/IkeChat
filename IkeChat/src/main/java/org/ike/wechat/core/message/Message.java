/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message
 * Author: Xuejia
 * Date Time: 2016/6/29 22:22
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message;

/**
 * Class Name: Message
 * Create Date: 2016/6/29 22:22
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class Message {
    private String ToUserName = null;                       // 开发者微信号
    private String FromUserName = null;                     // 发送方帐号（一个OpenID）
    private String CreateTime = null;                       // 消息创建时间 （整型）
    private String MsgType = null;                          // image
    private String MsgId = null;                            // 消息id，64位整型
    private String PicUrl = null;                           // 图片链接（由系统生成）
    private String Url = null;                              // 消息链接
    private String Title = null;                            // 消息标题
    private String Description = null;                      // 消息描述
    private String Content = null;                          // 文本消息内容
    private String MediaId = null;                          // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String Format = null;                           // 语音格式，如amr，speex等
    private String Recognition = null;                      // 语音识别结果，UTF8编码
    private String ThumbMediaId = null;                     // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
    private String Location_X = null;                       // 地理位置维度
    private String Location_Y = null;                       // 地理位置经度
    private String Scale = null;                            // 地图缩放大小
    private String Label = null;                            // 地理位置信息
}
