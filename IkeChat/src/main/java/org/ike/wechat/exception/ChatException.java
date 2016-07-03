/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.exception
 * Author: Xuejia
 * Date Time: 2016/6/11 21:24
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.ike.wechat.core.IkeChat;

import java.io.IOException;

/**
 * Class Name: ChatException
 * Create Date: 2016/6/11 21:24
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: API异常基类
 */
public class ChatException extends Throwable {

    Logger logger = Logger.getLogger(IkeChat.LOGGER_NAME);

    public ChatException() {
    }

    public ChatException(String message) {
        super(message);
    }

    public ChatException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatException(Throwable cause) {
        super(cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        // 对所有发生的api异常，将做一次授权信息的保存
        if (!this.getClass().getName().contains("Loading")) {
            IkeChat.getConfiguration().saveToken2Disk();
        }
        return super.fillInStackTrace();
    }

    protected static String parseError(String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(jsonData);
        } catch (IOException e) {
            jsonNode = null;
        }
        return parseError(jsonNode);
    }

    protected static String parseError(JsonNode node) {
        return WeChatErr.getError(node.get("errcode").asInt());
    }
}
