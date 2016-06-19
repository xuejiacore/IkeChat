/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/18 22:06
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

/**
 * Class Name: AbstractConfiguration
 * Create Date: 2016/6/18 22:06
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class AbstractConfiguration implements IConfiguration {

    /**
     * 公众号的appId
     */
    private static String appid = null;
    /**
     * 公众号的secretKey
     */
    private static String secretKey = null;

    /**
     * 基础Access Token
     */
    private static String ACCESS_TOKEN = null;
    /**
     * 基础Access Token 的有效时长
     */
    private static int ACCESS_TOKEN_EXPIRE_IN = 999999;
    /**
     * JS API调用ticket
     */
    private static String JS_TICKET = null;
    /**
     * JS API调用ticket的有效时长
     */
    private static int JS_TICKET_EXPIRE_IN = 999999;
    /**
     * web access token
     */
    private static String WEB_ACCESS_TOKEN = null;
    /**
     * web access token 的有效时长
     */
    private static int WEB_ACCESS_TOKEN_EXPIRE_IN = 999999;

    /**
     * access token 有效状态
     */
    private static boolean isAccessTokenEffective = true;
    /**
     * js ticket 的有效状态
     */
    private static boolean isJsTicketEffective = true;
    /**
     * web token的有效状态
     */
    private static boolean isWebTokenEffective = true;

    public String getAppid() {
        return AbstractConfiguration.appid;
    }


    public void setAppid(String appid) {
        AbstractConfiguration.appid = appid;
    }

    public String getSecretKey() {
        return AbstractConfiguration.secretKey;
    }

    public void setSecretKey(String secretKey) {
        AbstractConfiguration.secretKey = secretKey;
    }

    public String getAccessToken() {
        return AbstractConfiguration.ACCESS_TOKEN;
    }

    public void setAccessToken(String accessToken) {
        AbstractConfiguration.ACCESS_TOKEN = accessToken;
    }

    public int getAccessTokenExpireIn() {
        return AbstractConfiguration.ACCESS_TOKEN_EXPIRE_IN;
    }

    public void setAccessTokenExpireIn(int accessTokenExpireIn) {
        AbstractConfiguration.ACCESS_TOKEN_EXPIRE_IN = accessTokenExpireIn;
    }

    public String getJsTicket() {
        return AbstractConfiguration.JS_TICKET;
    }

    public void setJsTicket(String jsTicket) {
        AbstractConfiguration.JS_TICKET = jsTicket;
    }

    public int getJsTicketExpireIn() {
        return AbstractConfiguration.JS_TICKET_EXPIRE_IN;
    }

    public void setJsTicketExpireIn(int jsTicketExpireIn) {
        AbstractConfiguration.JS_TICKET_EXPIRE_IN = jsTicketExpireIn;
    }

    public String getWebAccessToken() {
        return AbstractConfiguration.WEB_ACCESS_TOKEN;
    }

    public void setWebAccessToken(String webAccessToken) {
        AbstractConfiguration.WEB_ACCESS_TOKEN = webAccessToken;
    }

    public int getWebAccessTokenExpireIn() {
        return AbstractConfiguration.WEB_ACCESS_TOKEN_EXPIRE_IN;
    }

    public void setWebAccessTokenExpireIn(int webAccessTokenExpireIn) {
        AbstractConfiguration.WEB_ACCESS_TOKEN_EXPIRE_IN = webAccessTokenExpireIn;
    }

    public boolean isAccessTokenEffective() {
        return AbstractConfiguration.isAccessTokenEffective;
    }

    public void setIsAccessTokenEffective(boolean isAccessTokenEffective) {
        AbstractConfiguration.isAccessTokenEffective = isAccessTokenEffective;
    }

    public boolean isJsTicketEffective() {
        return AbstractConfiguration.isJsTicketEffective;
    }

    public void setIsJsTicketEffective(boolean isJsTicketEffective) {
        AbstractConfiguration.isJsTicketEffective = isJsTicketEffective;
    }

    public boolean isWebTokenEffective() {
        return AbstractConfiguration.isWebTokenEffective;
    }

    public void setIsWebTokenEffective(boolean isWebTokenEffective) {
        AbstractConfiguration.isWebTokenEffective = isWebTokenEffective;
    }
}
