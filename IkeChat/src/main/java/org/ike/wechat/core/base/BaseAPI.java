/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.base
 * Author: Xuejia
 * Date Time: 2016/6/18 22:24
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.base;

import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.exception.ChatException;
import org.ike.wechat.exception.DeniedOperationException;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.parser.*;

import java.io.IOException;
import java.util.Map;

/**
 * Class Name: BaseAPI
 * Create Date: 2016/6/18 22:24
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 对话服务-基础支持接口
 * <p/>
 * 基础支持借口包含了刷新公众号的access token 以及获取公众号的服务器IP地址接口
 * <p/>
 * 每个帐号每月共10次清零操作机会，清零生效一次即用掉一次机会（10次包括了平台上的清零和调用接口API的清零）
 */
public class BaseAPI extends AbstractApi {

    private static final String CGI_REFRESH_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";                   // 刷新access token cgi
    private static final String CGI_SERVER_IPS = "https://api.weixin.qq.com/cgi-bin/getcallbackip";                           // 获取服务器的ip列表
    // 每个帐号每月共10次清零操作机会，清零生效一次即用掉一次机会（10次包括了平台上的清零和调用接口API的清零）
    private static final String CGI_CLEAR_QUOTA = "https://api.weixin.qq.com/cgi-bin/clear_quota?access_token=%s";                            // 对接口调用次数清零

    public IParameterKey[] getNecessaryParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_REFRESH_TOKEN)) {
                return new IParameterKey[0];
            } else if (apiIs(IkeChat.API_LIST_SERVER_IPS)) {
                return new IParameterKey[0];
            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        }
        return new IParameterKey[0];
    }

    public IParameterKey[] getOptionalParams(int apiId) {
        return new IParameterKey[0];
    }

    // TODO:这部分注意线程同步
    public Response req(int apiId, Parameters parameters) {
        try {
            parameters.put(new ParameterKey("access_token"), new ParameterValue(IkeChat.getAuthorInfo().getAccessToken()));
            if (apiIs(IkeChat.API_REFRESH_TOKEN)) {
                parameters.put(new ParameterKey("appid"), new ParameterValue(IkeChat.getAuthorInfo().getAppid()));
                parameters.put(new ParameterKey("secret"), new ParameterValue(IkeChat.getAuthorInfo().getSecretKey()));
                if (parameters.size() != 0 && (Boolean) (parameters.get("_release_lock").getValue())) {
                    IkeChat.releaseLocker();
                } else {
                    throw new DeniedOperationException("拒绝不安全的操作操作!");
                }
                Response response = new Response(apiId, httpsPostReq(CGI_REFRESH_TOKEN, parameters));
                Map resultMap = response.toMap();
                IkeChat.getAuthorInfo().setAccessToken((String) resultMap.get("access_token"));
                IkeChat.getAuthorInfo().setAccessTokenExpireIn((Integer) resultMap.get("expires_in"));
                return response;

            } else if (apiIs(IkeChat.API_LIST_SERVER_IPS)) {
                return new Response(apiId, httpsPostReq(CGI_SERVER_IPS, parameters));

            } else if (apiIs(IkeChat.API_CLEAR_QUOTA)) {
                if (parameters.size() != 0 && (Boolean) (parameters.get("_release_lock").getValue())) {
                    IkeChat.releaseLocker();
                } else {
                    throw new DeniedOperationException("拒绝不安全的操作操作!");
                }
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_CLEAR_QUOTA, IkeChat.getAuthorInfo().getAccessToken()),
                        "{\"appid\":\"" + IkeChat.getAuthorInfo().getAppid() + "\"}"));

            }
        } catch (UnverifiedParameterException unverifiedParameter) {
            unverifiedParameter.printStackTrace();
        } catch (DeniedOperationException e) {
            e.printStackTrace();
        } catch (ChatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
