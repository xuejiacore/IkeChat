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
     * 公众号的appId
     */
    private String appid = null;
    /**
     * 公众号的secretKey
     */
    private String secretKey = null;
    /**
     * 基础Access Token
     */
    private String ACCESS_TOKEN = null;
    /**
     * 基础Access Token 的有效时长
     */
    private int ACCESS_TOKEN_EXPIRE_IN = 0;
    /**
     * JS API调用ticket
     */
    private String JS_TICKET = null;
    /**
     * JS API调用ticket的有效时长
     */
    private int JS_TICKET_EXPIRE_IN = 0;
    /**
     * web access token
     */
    private String WEB_ACCESS_TOKEN = null;
    /**
     * web access token 的有效时长
     */
    private int WEB_ACCESS_TOKEN_EXPIRE_IN = 0;

    /**
     * access token 有效状态
     */
    private boolean isAccessTokenEffective = true;
    /**
     * js ticket 的有效状态
     */
    private boolean isJsTicketEffective = true;
    /**
     * web token的有效状态
     */
    private boolean isWebTokenEffective = true;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }

    public int getAccessTokenExpireIn() {
        return ACCESS_TOKEN_EXPIRE_IN;
    }

    public void setAccessTokenExpireIn(int accessTokenExpireIn) {
        ACCESS_TOKEN_EXPIRE_IN = accessTokenExpireIn;
    }

    public String getJsTicket() {
        return JS_TICKET;
    }

    public void setJsTicket(String jsTicket) {
        JS_TICKET = jsTicket;
    }

    public int getJsTicketExpireIn() {
        return JS_TICKET_EXPIRE_IN;
    }

    public void setJsTicketExpireIn(int jsTicketExpireIn) {
        JS_TICKET_EXPIRE_IN = jsTicketExpireIn;
    }

    public String getWebAccessToken() {
        return WEB_ACCESS_TOKEN;
    }

    public void setWebAccessToken(String webAccessToken) {
        WEB_ACCESS_TOKEN = webAccessToken;
    }

    public int getWebAccessTokenExpireIn() {
        return WEB_ACCESS_TOKEN_EXPIRE_IN;
    }

    public void setWebAccessTokenExpireIn(int webAccessTokenExpireIn) {
        WEB_ACCESS_TOKEN_EXPIRE_IN = webAccessTokenExpireIn;
    }

    public boolean isAccessTokenEffective() {
        return isAccessTokenEffective;
    }

    public void setIsAccessTokenEffective(boolean isAccessTokenEffective) {
        this.isAccessTokenEffective = isAccessTokenEffective;
    }

    public boolean isJsTicketEffective() {
        return isJsTicketEffective;
    }

    public void setIsJsTicketEffective(boolean isJsTicketEffective) {
        this.isJsTicketEffective = isJsTicketEffective;
    }

    public boolean isWebTokenEffective() {
        return isWebTokenEffective;
    }

    public void setIsWebTokenEffective(boolean isWebTokenEffective) {
        this.isWebTokenEffective = isWebTokenEffective;
    }

    @Override
    public String toString() {
        return "AuthorInfo{" +
                "appid='" + appid + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", ACCESS_TOKEN='" + ACCESS_TOKEN + '\'' +
                ", ACCESS_TOKEN_EXPIRE_IN=" + ACCESS_TOKEN_EXPIRE_IN +
                ", JS_TICKET='" + JS_TICKET + '\'' +
                ", JS_TICKET_EXPIRE_IN=" + JS_TICKET_EXPIRE_IN +
                ", WEB_ACCESS_TOKEN='" + WEB_ACCESS_TOKEN + '\'' +
                ", WEB_ACCESS_TOKEN_EXPIRE_IN=" + WEB_ACCESS_TOKEN_EXPIRE_IN +
                ", isAccessTokenEffective=" + isAccessTokenEffective +
                ", isJsTicketEffective=" + isJsTicketEffective +
                ", isWebTokenEffective=" + isWebTokenEffective +
                '}';
    }
}
