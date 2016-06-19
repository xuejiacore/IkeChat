/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.exception
 * Author: Xuejia
 * Date Time: 2016/6/11 21:39
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.exception;

/**
 * Class Name: WeChatException
 * Create Date: 2016/6/11 21:39
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class WeChatException extends ChatException {

    public WeChatException(String message) {
        super(message);
    }

    public static void main(String[] args) throws WeChatException {
        throw new WeChatException(WeChatException.parseError("{\"errcode\":40001}"));
    }
}
