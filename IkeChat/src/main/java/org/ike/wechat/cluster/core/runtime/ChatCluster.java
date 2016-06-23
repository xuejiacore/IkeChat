/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.cluster.core.runtime
 * Author: Xuejia
 * Date Time: 2016/6/22 21:17
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.cluster.core.runtime;

import org.ike.wechat.cluster.core.config.ChatNode;
import org.ike.wechat.core.auth.AuthorInfo;

import java.util.HashMap;

/**
 * Class Name: ChatCluster
 * Create Date: 2016/6/22 21:17
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 公众号群集容器
 */
public class ChatCluster implements INodeChangeListener {
    private static HashMap<String, AuthorInfo> authorInfos = null;

    private static HashMap<String, ChatNode> chatNodes = null;

    public synchronized void onNodeCreate(ChatNode chatNode, AuthorInfo authorInfo) {
        authorInfos.put(chatNode.getAppId(), authorInfo);
    }

    public synchronized void onNodeRemove(ChatNode chatNode, AuthorInfo authorInfo) {
        authorInfos.remove(chatNode.getAppId());
    }
}
