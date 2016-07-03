/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/7/2 17:00
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

import org.ike.wechat.cache.ICache;
import org.ike.wechat.core.auth.AuthorInfo;

/**
 * Class Name: WebDefaultFlieCache
 * Create Date: 2016/7/2 17:00
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class WebDefaultFlieCache implements ICache {
    public boolean onCache(AuthorInfo info) {
        return false;
    }

    public AuthorInfo onCacheLoading() {
        return null;
    }
}
