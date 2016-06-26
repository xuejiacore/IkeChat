/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.log
 * Author: Xuejia
 * Date Time: 2016/6/26 17:46
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.log;

import org.ike.wechat.core.auth.AuthorInfo;

/**
 * Class Name: IResponseListener
 * Create Date: 2016/6/26 17:46
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public interface IResponseListener {
    /**
     * API调用成功回调
     *
     * @param apiId      调用的api
     * @param msg        事件消息
     * @param authorInfo 授权信息
     */
    void onSuccess(int apiId, String msg, AuthorInfo authorInfo);

    /**
     * API调用失败回调
     *
     * @param apiId      调用的API
     * @param msg        事件消息
     * @param authorInfo 授权信息
     */
    void onFailure(int apiId, String msg, AuthorInfo authorInfo);
}
