/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.parser
 * Author: Xuejia
 * Date Time: 2016/6/18 20:59
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.exception.ChatException;
import org.ike.wechat.exception.UnknownResponseException;
import org.ike.wechat.exception.WeChatErr;
import org.ike.wechat.log.IResponseListener;

import java.io.IOException;
import java.util.HashMap;

/**
 * Class Name: Response
 * Create Date: 2016/6/18 20:59
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:请求响应
 */
public class Response {
    private String jsonResult = null;

    /**
     * 初始化响应
     *
     * @param apiId 调用的apiId
     * @param data  响应的原始数据
     */
    public Response(int apiId, String data) throws IOException, ChatException {
        this.jsonResult = data;
        IResponseListener responseListener = IkeChat.getConfiguration().getResponseListener();
        if (this.jsonResult.startsWith("{") || this.jsonResult.startsWith("[")) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(data);
            int errorCode;

            if (this.jsonResult.contains("errcode") && (errorCode = jsonNode.get("errcode").asInt()) != 0) {
                String errorMsg;
                errorMsg = WeChatErr.getError(errorCode);

                if (responseListener != null) {
                    responseListener.onFailure(apiId, errorMsg, IkeChat.getAuthorInfo());
                }
                throw new ChatException(errorMsg);
            } else {
                if (responseListener != null) {
                    responseListener.onSuccess(apiId, "Successful!", IkeChat.getAuthorInfo());
                }
            }
        } else {
            if (responseListener != null) {
                responseListener.onFailure(apiId, "Unknown Response Exception", IkeChat.getAuthorInfo());
            }
            throw new UnknownResponseException("Unknown Response Exception");
        }
    }

    /**
     * 将结果转化为对应的对象
     *
     * @param domain 需要转化的对象类
     * @return 返回实例化后的对象
     */
    public Object toClz(Class domain) {
        return new Gson().fromJson(this.jsonResult, domain);
    }

    public HashMap toMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap map = null;
        try {
            map = objectMapper.readValue(this.jsonResult, HashMap.class);
        } catch (IOException e) {
            try {
                throw new UnknownResponseException("无法对非Json响应内容进行Map转化");
            } catch (UnknownResponseException unknownResponse) {
                unknownResponse.printStackTrace();
            }
        }
        return map;
    }

    @Override
    public String toString() {
        return this.jsonResult;
    }
}
