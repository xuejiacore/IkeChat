/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.auth
 * Author: Xuejia
 * Date Time: 2016/6/11 20:20
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.ike.wechat.core.auth;

/**
 * Class Name: AuthorInfo
 * Create Date: 2016/6/11 20:20
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 授权信息
 */
public class AuthorInfo {
    /**
     * 基础 access token
     */
    private static String accessToken = null;
    /**
     * access token 的超时时长
     */
    private static int accessTokenExpireIn = Integer.MAX_VALUE;
    /**
     * js api 调用所需的Ticket
     */
    private static String jsApiTicket = null;
    /**
     * js api 的超时时长
     */
    private static int jsApiTokenExpireIn = Integer.MAX_VALUE;




}
