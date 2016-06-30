/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message.listener
 * Author: Xuejia
 * Date Time: 2016/6/30 14:17
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message.listener;

import org.ike.wechat.core.message.domain.event.IEvent;
import org.ike.wechat.core.message.domain.simple.IMessage;

/**
 * Class Name: IListener
 * Create Date: 2016/6/30 14:17
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public interface IListener {
    /**
     * 关注消息监听器
     *
     * @param event 事件消息体
     */
    void onSubscribeListener(IEvent event);

    /**
     * 取消关注消息监听器
     *
     * @param event 事件消息体
     */
    void onUnsubscribeListener(IEvent event);

    /**
     * 扫描场景二维码消息监听器
     *
     * @param event 事件消息体
     */
    void onScanListener(IEvent event);

    /**
     * 上传位置消息监听器
     *
     * @param event 事件消息体
     */
    void onLocationListener(IEvent event);

    /**
     * 点击菜单拉取消息监听器
     *
     * @param event 事件消息体
     */
    void onClickListener(IEvent event);

    /**
     * 点击菜单跳转事件消息监听器
     *
     * @param event 事件消息体
     */
    void onViewListener(IEvent event);

    void onTextMsgReceived(IMessage msg);

    void onImageMsgReceived(IMessage msg);

    void onVoiceMsgReceived(IMessage msg);

    void onVideoMsgReceived(IMessage msg);

    void onShortVideoMsgReceived(IMessage msg);

    void onLocationMsgReceived(IMessage msg);

    void onLinkMsgReceived(IMessage msg);
}
