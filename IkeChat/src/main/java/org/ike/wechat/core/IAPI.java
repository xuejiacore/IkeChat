/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core
 * Author: Xuejia
 * Date Time: 2016/6/13 23:56
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core;

import org.ike.wechat.parser.IParameterKey;
import org.ike.wechat.parser.Parameters;
import org.ike.wechat.parser.Response;

/**
 * Class Name: IAPI
 * Create Date: 2016/6/13 23:56
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: API接口
 */
public interface IAPI {

    int SSH_POST = 1;
    int SSH_GET = 2;
    int GET = 3;
    int POST = 4;

    /**
     * 获得必要参数的参数键
     *
     * @param apiId 调用的API ID
     * @return 返回必要参数的参数键数组
     */
    IParameterKey[] getNecessaryParams(int apiId);

    /**
     * 获取可选参数的参数键
     *
     * @param apiId 调用的API ID
     * @return 返回可选的参数键数组
     */
    IParameterKey[] getOptionalParams(int apiId);

    /**
     * 调用对应的API
     *
     * @param apiId      需要调用的API ID
     * @param parameters 调用API ID所需要的参数
     * @return 返回响应体
     */
    Response req(int apiId, Parameters parameters);
}
