/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.cluster.core.config
 * Author: Xuejia
 * Date Time: 2016/6/22 20:33
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.cluster.core.config;

import org.ike.wechat.core.auth.AuthorInfo;

import java.util.HashMap;

/**
 * Class Name: AbstractClusterConfig
 * Create Date: 2016/6/22 20:33
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 集群公众号的配置抽象类
 */
public class AbstractClusterConfig implements IClusterConfiguration {
    // 微信集群的配置集合
    private static HashMap<String, ChatNode> chatClusterConf = null;

    // 所有加入到集群中的配置的集合
    private static HashMap<String, AuthorInfo> authorInfos = null;

    public AbstractClusterConfig() {
        chatClusterConf = new HashMap<String, ChatNode>();

        // TODO:配置文件中读取集群的配置信息，对公众号集群进行配置初始化
    }

}
