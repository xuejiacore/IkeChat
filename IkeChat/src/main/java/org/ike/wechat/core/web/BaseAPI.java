/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.web
 * Author: Xuejia
 * Date Time: 2016/6/19 17:56
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.web;

import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.parser.IParameterKey;
import org.ike.wechat.parser.Parameters;
import org.ike.wechat.parser.Response;

/**
 * Class Name: BaseAPI
 * Create Date: 2016/6/19 17:56
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 网页服务-基础借口
 */
public class BaseAPI extends AbstractApi {
    public IParameterKey[] getNecessaryParams(int apiId) {
        return new IParameterKey[0];
    }

    public IParameterKey[] getOptionalParams(int apiId) {
        return new IParameterKey[0];
    }

    public Response req(int apiId, Parameters parameters) {
        return null;
    }
}
