/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.cluster.core.runtime
 * Author: Xuejia
 * Date Time: 2016/6/22 21:18
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.cluster.core.runtime;

import org.ike.wechat.cluster.core.config.ChatNode;
import org.ike.wechat.core.auth.AuthorInfo;

/**
 * Class Name: INodeChangeListener
 * Create Date: 2016/6/22 21:18
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 公众号节点变动监听器
 */
public interface INodeChangeListener {
    /**
     * 节点创建回调
     *
     * @param chatNode   新增的公众号节点
     * @param authorInfo 新增的公众号认证信息
     */
    void onNodeCreate(ChatNode chatNode, AuthorInfo authorInfo);

    /**
     * 节点删除回调
     *
     * @param chatNode   删除的公众号节点
     * @param authorInfo 删除的公众号认证信息
     */
    void onNodeRemove(ChatNode chatNode, AuthorInfo authorInfo);

}
