/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.cluster
 * Author: Xuejia
 * Date Time: 2016/6/22 20:17
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.cluster.core.config;

/**
 * Class Name: ChatNode
 * Create Date: 2016/6/22 20:17
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 微信公众号节点
 */
public class ChatNode {

    // 公众号节点的别称
    private String alias = null;
    // 应用的ID
    private String appId = null;
    // 应用的密钥
    private String appSecret = null;
    // 服务器的地址
    private String serverUrl = null;
    // 消息加解密密钥
    private String encodingAESKey = null;
    // 消息加解密方式
    private int encryptType = 0;
    // 令牌
    private String token = null;
    // js回调域名1
    private String jsDomain1 = null;
    // js回调域名2
    private String jsDomain2 = null;
    // js回调域名3
    private String jsDomain3 = null;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public int getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(int encryptType) {
        this.encryptType = encryptType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJsDomain1() {
        return jsDomain1;
    }

    public void setJsDomain1(String jsDomain1) {
        this.jsDomain1 = jsDomain1;
    }

    public String getJsDomain2() {
        return jsDomain2;
    }

    public void setJsDomain2(String jsDomain2) {
        this.jsDomain2 = jsDomain2;
    }

    public String getJsDomain3() {
        return jsDomain3;
    }

    public void setJsDomain3(String jsDomain3) {
        this.jsDomain3 = jsDomain3;
    }

    @Override
    public String toString() {
        return "ChatNode{" +
                "alias='" + alias + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", serverUrl='" + serverUrl + '\'' +
                ", encodingAESKey='" + encodingAESKey + '\'' +
                ", encryptType=" + encryptType +
                ", token='" + token + '\'' +
                ", jsDomain1='" + jsDomain1 + '\'' +
                ", jsDomain2='" + jsDomain2 + '\'' +
                ", jsDomain3='" + jsDomain3 + '\'' +
                '}';
    }
}
