/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.exception
 * Author: Xuejia
 * Date Time: 2016/7/2 11:38
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.exception;

/**
 * Class Name: LoadingConfigException
 * Create Date: 2016/7/2 11:38
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:配置加载异常
 */
public class LoadingConfigException extends ChatException {
    public LoadingConfigException(String message) {
        super(message);
    }

    public LoadingConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
