/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/11 20:13
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

import org.ike.wechat.core.auth.AuthorInfo;

/**
 * Class Name: IConfiguration
 * Create Date: 2016/6/11 20:13
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 微信配置接口
 */
public interface IConfiguration {
    /**
     * 设置凭证信息到配置中
     *
     * @param authorInfo 凭证信息
     * @return 返回当前的配置实例
     */
    IConfiguration setAuthorInfo(AuthorInfo authorInfo);

    /**
     * 从配置中获得凭证信息
     *
     * @return 凭证信息
     */
    AuthorInfo getAuthorInfo();
}
