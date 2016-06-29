/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.web
 * Author: Xuejia
 * Date Time: 2016/6/28 18:54
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.web;

/**
 * Class Name: ScopeType
 * Create Date: 2016/6/28 18:54
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 授权作用域
 */
public interface ScopeType {
    /**
     * 不弹出授权页面，直接跳转，只能获取用户openid
     */
    String SCOPE_BASE = "snsapi_base";
    /**
     * 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息
     */
    String SCOPE_DETAIL = "snsapi_userinfo";
}
