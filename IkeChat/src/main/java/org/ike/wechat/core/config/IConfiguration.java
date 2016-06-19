/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/11 20:13
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

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
    String getAppid();

    void setAppid(String appid);

    String getSecretKey();

    void setSecretKey(String secretKey);

    String getAccessToken();

    void setAccessToken(String accessToken);

    int getAccessTokenExpireIn();

    void setAccessTokenExpireIn(int accessTokenExpireIn);

    String getJsTicket();

    void setJsTicket(String jsTicket);

    int getJsTicketExpireIn();

    void setJsTicketExpireIn(int jsTicketExpireIn);

    String getWebAccessToken();

    void setWebAccessToken(String webAccessToken);

    int getWebAccessTokenExpireIn();

    void setWebAccessTokenExpireIn(int webAccessTokenExpireIn);

    boolean isAccessTokenEffective();

    void setIsAccessTokenEffective(boolean isAccessTokenEffective);

    boolean isJsTicketEffective();

    void setIsJsTicketEffective(boolean isJsTicketEffective);

    boolean isWebTokenEffective();

    void setIsWebTokenEffective(boolean isWebTokenEffective);
}
