/**
 * Project: IkeChat
 * Package Name: org.ike.wechat
 * Author: Xuejia
 * Date Time: 2016/6/14 13:56
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat;

import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.parser.IParameterKey;
import org.ike.wechat.parser.ParameterKey;
import org.ike.wechat.parser.Parameters;
import org.ike.wechat.parser.Response;

/**
 * Class Name: TestAPI
 * Create Date: 2016/6/14 13:56
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class TestAPI extends AbstractApi {

    private static final int API_DEMO = 0x03;
    private static final int API_DEMO2 = 0x05;

    public IParameterKey[] getNecessaryParams(int apiId) {
        return new IParameterKey[]{new ParameterKey("key1"), new ParameterKey("key2")};
    }

    public IParameterKey[] getOptionalParams(int apiId) {
        return new IParameterKey[0];
    }

    public Response req(int apiId, Parameters parameters) {
        this.apiId = apiId;
        try {
            if (apiIs(API_DEMO)) {
                System.err.println("API_DEMO");
            } else if (apiIs(API_DEMO2)) {
                System.err.println("API_DEMO2");
            }
        } catch (UnverifiedParameterException unverifiedParameter) {
            unverifiedParameter.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        TestAPI testAPI = new TestAPI();
        testAPI.req(API_DEMO2, new Parameters());
    }
}
