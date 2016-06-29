/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.acc
 * Author: Xuejia
 * Date Time: 2016/6/24 22:19
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.acc;

import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.exception.ChatException;
import org.ike.wechat.exception.InvalidateAPIException;
import org.ike.wechat.exception.InvalidateParametersException;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.parser.*;

import java.io.IOException;

/**
 * Class Name: WebAPI
 * Create Date: 2016/6/24 22:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class AccountAPI extends AbstractApi {

    private static final String CGI_CREATE_SCENE_QR = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";    // 创建二维码
    private static final String CGI_LURL_2_SURL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=%s";             // 长地址转化为短地址

    public IParameterKey[] getNecessaryParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_AC_CREATE_QR)) {
                return new IParameterKey[]{new ParameterKey("action_name", "二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值 ")};
            } else if (apiIs(IkeChat.API_LURL_2_SURL)) {
                return new IParameterKey[]{new ParameterKey("long_url", "需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url ")};
            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        }
        return new IParameterKey[0];
    }

    public IParameterKey[] getOptionalParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_AC_CREATE_QR)) {
                return new IParameterKey[]{new ParameterKey("expire_seconds",
                        "该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。"),
                        new ParameterKey("scene_id", "场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）"),
                        new ParameterKey("scene_str", "场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段")};
            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        }
        return new IParameterKey[0];
    }

    public Response req(int apiId, Parameters parameters) {
        try {
            if (apiIs(IkeChat.API_AC_CREATE_QR)) {
                String createType = parameters.get("action_name").getValue().toString();
                if (createType.equals("QR_SCENE")) {
                    return new Response(apiId,httpsJsonPostReq(String.format(CGI_CREATE_SCENE_QR, IkeChat.getAuthorInfo().getAccessToken()), "" +
                            "{\"expire_seconds\": " + parameters.getOrDef("expire_seconds", 30) + ", \"action_name\": \"" +
                            createType + "\", \"action_info\": {\"scene\": {\"scene_id\": " +
                            parameters.get("scene_id").getValue() + "}}}"));
                } else {
                    return new Response(apiId,httpsJsonPostReq(String.format(CGI_CREATE_SCENE_QR, IkeChat.getAuthorInfo().getAccessToken()), "" +
                            "{\"expire_seconds\": " + parameters.getOrDef("expire_seconds", 30) + ", \"action_name\": \"" +
                            createType + "\", \"action_info\": {\"scene\": {\"scene_id\": " +
                            parameters.get("scene_id").getValue() + ",\"scene_str\":\"" + parameters.get("scene_str").getValue() + "\"}}}"));
                }
            } else if (apiIs(IkeChat.API_LURL_2_SURL)) {
                return new Response(apiId,httpsJsonPostReq(String.format(CGI_LURL_2_SURL, IkeChat.getAuthorInfo().getAccessToken()),
                        "{\"action\":\"long2short\",\"long_url\":\"" + parameters.get("long_url").getValue() + "\""));
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

    public static void main(String[] args) throws ChatException, IOException {
        IkeChat.loadConfiguration(new DefaultConfiguration());

//        IkeChat.req(IkeChat.API_REFRESH_TOKEN, IkeChat.PARAM_RELEASE_LOCKER);
//        System.err.println(IkeChat.req(IkeChat.API_AC_CREATE_QR, new Object[][]{{"action_name", "QR_LIMIT_SCENE"}, {"scene_str", "http://demo.foo.com"}}));
        System.err.println(IkeChat.req(IkeChat.API_LURL_2_SURL, new Object[][]{{"long_url", "http://www.baidu.com"}}).toMap());

    }
}
