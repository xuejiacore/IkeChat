/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.config
 * Author: Xuejia
 * Date Time: 2016/6/22 19:59
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

import org.ike.wechat.core.auth.AuthorInfo;

/**
 * Class Name: TokenUnavailableListener
 * Create Date: 2016/6/22 19:59
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 凭证失效监听器
 */
public interface TokenUnavailableListener {
    void onTokenUnavailable(AuthorInfo authorInfo);
}
