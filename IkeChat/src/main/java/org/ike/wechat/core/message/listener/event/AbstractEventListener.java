/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message.listener.event
 * Author: Xuejia
 * Date Time: 2016/6/30 17:36
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message.listener.event;

import org.ike.wechat.core.message.domain.simple.IMessage;

/**
 * Class Name: AbstractEventListener
 * Create Date: 2016/6/30 17:36
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public abstract class AbstractEventListener implements IEventListener{
    public void onImageMsgReceived(IMessage msg) {

    }

    public void onVoiceMsgReceived(IMessage msg) {

    }

    public void onTextMsgReceived(IMessage msg) {

    }

    public void onVideoMsgReceived(IMessage msg) {

    }

    public void onShortVideoMsgReceived(IMessage msg) {

    }

    public void onLocationMsgReceived(IMessage msg) {

    }

    public void onLinkMsgReceived(IMessage msg) {

    }
}
