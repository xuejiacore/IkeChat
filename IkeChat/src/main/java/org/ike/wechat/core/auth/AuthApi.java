/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.auth
 * Author: Xuejia
 * Date Time: 2016/6/18 22:19
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.ike.wechat.core.auth;

import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.parser.IParameterKey;
import org.ike.wechat.parser.Parameters;
import org.ike.wechat.parser.Response;

/**
 * Class Name: AuthApi
 * Create Date: 2016/6/18 22:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class AuthApi extends AbstractApi {




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
