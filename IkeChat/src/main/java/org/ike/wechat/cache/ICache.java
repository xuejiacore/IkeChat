/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/21 7:26
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.cache;

import org.ike.wechat.core.auth.AuthorInfo;

/**
 * Class Name: ICache
 * Create Date: 2016/6/21 7:26
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 凭证的缓存接口
 */
public interface ICache {
    /**
     * 缓存凭证信息
     *
     * @return 调用缓存接口对凭证信息进行存储
     */
    boolean onCache(AuthorInfo info);

    /**
     * 从缓存中获取凭证信息
     *
     * @return 调用缓存接口加载缓存信息
     */
    AuthorInfo onCacheLoading();
}
