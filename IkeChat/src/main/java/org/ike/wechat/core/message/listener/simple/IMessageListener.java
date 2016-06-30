/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message
 * Author: Xuejia
 * Date Time: 2016/6/29 22:33
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message.listener.simple;

import org.ike.wechat.core.message.domain.simple.IMessage;
import org.ike.wechat.core.message.listener.IListener;

/**
 * Class Name: IMessageListener
 * Create Date: 2016/6/29 22:33
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public interface IMessageListener extends IListener {
    void onTextMsgReceived(IMessage msg);

    void onImageMsgReceived(IMessage msg);

    void onVoiceMsgReceived(IMessage msg);

    void onVideoMsgReceived(IMessage msg);

    void onShortVideoMsgReceived(IMessage msg);

    void onLocationMsgReceived(IMessage msg);

    void onLinkMsgReceived(IMessage msg);
}
