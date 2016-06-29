/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.web
 * Author: Xuejia
 * Date Time: 2016/6/19 17:56
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.web;

import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.exception.ChatException;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.parser.*;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Class Name: WebAPI
 * Create Date: 2016/6/19 17:56
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 网页服务-网页账号
 */
public class WebAPI extends AbstractApi {

    private static final String CGI_GENERATE_REDIRECT = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=%s&redirect_uri=%s&response_type=%s&scope=%s&state=%s#wechat_redirect";                      // 生成重定向地址
    private static final String CGI_EXCHANGE_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "grant_type=authorization_code";                                         // 换取access_token
    private static final String CGI_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?" +
            "grant_type=refresh_token";                                               // 刷新access_token
    private static final String CGI_FETCH_USER_DETAIL = "https://api.weixin.qq.com/sns/userinfo";// 拉取用户的信息
    private static final String CGI_CHECK_TOKEN = "https://api.weixin.qq.com/sns/auth";


    public IParameterKey[] getNecessaryParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_WEB_GENERATE_REDIRECT)) {
                return new IParameterKey[]{
                        new ParameterKey("redirect_uri", "授权后重定向的回调链接地址，请使用urlencode对链接进行处理")
                };

            } else if (apiIs(IkeChat.API_WEB_EXCHANGE_TOKEN)) {
                return new IParameterKey[]{
                        new ParameterKey("code", "获取的code参数")
                };

            } else if (apiIs(IkeChat.API_WEB_REFRESH_TOKEN)) {
                return new IParameterKey[]{
                        new ParameterKey("refresh_token", "通过access_token获取到的refresh_token参数")
                };

            } else if (apiIs(IkeChat.API_WEB_FETCH_USER_INFO)) {
                return new IParameterKey[]{new ParameterKey("openid", "用户的唯一标识")};

            } else if (apiIs(IkeChat.API_WEB_CHECK_TOKEN)) {
                return new IParameterKey[]{
                        new ParameterKey("openid", "用户的唯一标识"),
                        new ParameterKey("access_token", "用户对应的web access token")
                };
            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        }
        return new IParameterKey[0];
    }

    public IParameterKey[] getOptionalParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_WEB_GENERATE_REDIRECT)) {
                return new IParameterKey[]{
                        new ParameterKey("state", "重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节"),
                        new ParameterKey("scope", "请参考：" + ScopeType.class.getName() + "，默认为Base类型")
                };

            } else if (apiIs(IkeChat.API_WEB_FETCH_USER_INFO)) {
                return new IParameterKey[]{new ParameterKey("lang", "国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语")};

            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        }
        return new IParameterKey[0];
    }

    public Response req(int apiId, Parameters parameters) {
        try {
            if (apiIs(IkeChat.API_WEB_GENERATE_REDIRECT)) {
                return new Response(apiId, String.format(CGI_GENERATE_REDIRECT, IkeChat.getAuthorInfo().getAppid(),
                        URLEncoder.encode((String) parameters.get("redirect_uri").getValue(), "UTF-8"), "code",
                        parameters.getOrDef("scope", ScopeType.SCOPE_BASE), parameters.getOrDef("state", "")), true);

            } else if (apiIs(IkeChat.API_WEB_EXCHANGE_TOKEN)) {
                parameters.put(new ParameterKey("appid"), new ParameterValue(IkeChat.getAuthorInfo().getAppid()));
                parameters.put(new ParameterKey("secret"), new ParameterValue(IkeChat.getAuthorInfo().getSecretKey()));
                return new Response(apiId, httpsPostReq(CGI_EXCHANGE_TOKEN, parameters));

            } else if (apiIs(IkeChat.API_WEB_REFRESH_TOKEN)) {
                parameters.put(new ParameterKey("appid"), new ParameterValue(IkeChat.getAuthorInfo().getAppid()));
                return new Response(apiId, httpsPostReq(CGI_REFRESH_TOKEN, parameters));

            } else if (apiIs(IkeChat.API_WEB_FETCH_USER_INFO)) {
                parameters.put(new ParameterKey("access_token"), new ParameterValue(IkeChat.getAuthorInfo().getAccessToken()));
                return new Response(apiId, httpsPostReq(CGI_FETCH_USER_DETAIL, parameters));

            } else if (apiIs(IkeChat.API_WEB_CHECK_TOKEN)) {
                return new Response(apiId, httpsPostReq(CGI_CHECK_TOKEN, parameters));

            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        } catch (ChatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ChatException {
        IkeChat.loadConfiguration(new DefaultConfiguration());
//        IkeChat.req(IkeChat.API_REFRESH_TOKEN, IkeChat.PARAM_RELEASE_LOCKER);

//        System.err.println(IkeChat.req(IkeChat.API_WEB_GENERATE_REDIRECT, new Object[][]{{"redirect_uri", "http://www.baidu.com"}}));
//        System.err.println(IkeChat.req(IkeChat.API_WEB_EXCHANGE_TOKEN, new Object[][]{{"code", "http://www.baidu.com"}}));
//        System.err.println(IkeChat.req(IkeChat.API_WEB_REFRESH_TOKEN, new Object[][]{{"refresh_token", "http://www.baidu.com"}}));
//        System.err.println(IkeChat.req(IkeChat.API_WEB_CHECK_TOKEN, new Object[][]{{"access_token", "tokenabc"}, {"openid", "openidabc"}}));
    }

}
