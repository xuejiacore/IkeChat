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
import org.ike.wechat.exception.DeniedOperationException;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.parser.IParameterKey;
import org.ike.wechat.parser.Parameters;
import org.ike.wechat.parser.Response;

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
 */
public class BaseAPI extends AbstractApi {

    // 刷新access token cgi
    private static final String CGI_REFRESH_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
            "&appid=%s&secret=%s";

    // 获取服务器的ip列表
    private static final String CGI_SERVER_IPS = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=%s";

    public IParameterKey[] getNecessaryParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_REFRESH_TOKEN)) {
                return new IParameterKey[0];
            } else if (apiIs(IkeChat.API_LIST_SERVER_IPS)) {
                return new IParameterKey[0];
            }
        } catch (UnverifiedParameterException unverifiedParameter) {
            unverifiedParameter.printStackTrace();
        }
        return null;
    }

    public IParameterKey[] getOptionalParams(int apiId) {
        return new IParameterKey[0];
    }

    public Response req(int apiId, Parameters parameters) {
        try {
            if (apiIs(IkeChat.API_REFRESH_TOKEN)) {
                if (parameters != null && parameters.size() != 0 && (Boolean) (parameters.get("_release_lock").getValue())) {
                    IkeChat.releaseLocker();
                } else {
                    throw new DeniedOperationException("拒绝不安全的操作操作!");
                }
                Response response = new Response(httpsPostReq(String.format(CGI_REFRESH_TOKEN,
                        IkeChat.getAuthorInfo().getAppid(),
                        IkeChat.getAuthorInfo().getSecretKey()), parameters));
                Map resultMap = response.toMap();
                IkeChat.getAuthorInfo().setAccessToken((String) resultMap.get("access_token"));
                IkeChat.getAuthorInfo().setAccessTokenExpireIn((Integer) resultMap.get("expires_in"));
                return response;
            } else if (apiIs(IkeChat.API_LIST_SERVER_IPS)) {
                return new Response(httpsGetReq(String.format(CGI_SERVER_IPS, IkeChat.getAuthorInfo().getAccessToken()), null));
            }
        } catch (UnverifiedParameterException unverifiedParameter) {
            unverifiedParameter.printStackTrace();
        } catch (DeniedOperationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
