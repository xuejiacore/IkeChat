/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/11 20:14
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.auth.AuthorInfo;
import org.ike.wechat.utils.PropertiesUtil;

/**
 * Class Name: DefaultConfiguration
 * Create Date: 2016/6/11 20:14
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 微信默认配置类
 */
public class DefaultConfiguration extends AbstractConfiguration {

    public DefaultConfiguration() {
        AuthorInfo authorInfo = new AuthorInfo();
        authorInfo.setAppid(PropertiesUtil.getValue(IkeChat.P_APP_ID));
        authorInfo.setSecretKey(PropertiesUtil.getValue(IkeChat.P_SECRET));
        setAuthorInfo(authorInfo);
    }

    public IConfiguration setAuthorInfo(AuthorInfo authorInfo) {
        this.authorInfo = authorInfo;
        return this;
    }

    public AuthorInfo getAuthorInfo() {
        return this.authorInfo;
    }

    @Override
    public IStorage initStorageProcessor() {
        return new DefaultFileStorage();
    }
}
