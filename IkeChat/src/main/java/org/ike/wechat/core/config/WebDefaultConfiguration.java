/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/7/2 12:32
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

import org.ike.wechat.cache.ICache;
import org.ike.wechat.core.auth.AuthorInfo;
import org.ike.wechat.log.IResponseListener;

/**
 * Class Name: WebDefaultConfiguration
 * Create Date: 2016/7/2 12:32
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class WebDefaultConfiguration extends AbstractConfiguration {
    @Override
    public ICache initStorageProcessor() {
        return Configuration.getCache();
    }

    public IConfiguration setAuthorInfo(AuthorInfo authorInfo) {
        return null;
    }

    public AuthorInfo getAuthorInfo() {
        return null;
    }

    public IResponseListener getResponseListener() {
        return Configuration.getResponseListener();
    }
}
