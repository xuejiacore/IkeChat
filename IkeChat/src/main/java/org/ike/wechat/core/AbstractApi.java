/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core
 * Author: Xuejia
 * Date Time: 2016/6/14 0:05
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core;

import org.ike.wechat.exception.InvalidateParametersException;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.network.NetworkKit;
import org.ike.wechat.parser.IParameterKey;
import org.ike.wechat.parser.Parameters;

import java.io.IOException;

/**
 * Class Name: AbstractApi
 * Create Date: 2016/6/14 0:05
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 抽象的接口
 */
public abstract class AbstractApi implements IAPI {

    protected int apiId = -1;

    /**
     * 根据参数类型检查必要参数是否传入，如果没有传入，将报
     *
     * @param apiId      API调用ID
     * @param parameters API调用参数
     * @return 如果验证通过，那么返回值为true，否则返回值为false
     * @throws InvalidateParametersException
     */
    public boolean validateApiParams(int apiId, Parameters parameters) throws InvalidateParametersException {
        this.apiId = apiId;
        IParameterKey[] parameterKeys = getNecessaryParams(apiId);
        for (IParameterKey parameterKey : parameterKeys) {
            if (!parameters.containsKey(parameterKey)) {
                StringBuilder sb = new StringBuilder("[");
                for (IParameterKey key : parameterKeys) {
                    sb.append(key.getKey()).append(", ");
                }
                throw new InvalidateParametersException(String.format("缺少必要参数 %s 中的 %s",
                        sb.substring(0, sb.length() - 2) + "]", parameterKey));
            }
        }
        return true;
    }

    /**
     * 跟据API的ID判断是否调用某一个API
     *
     * @param apiId 需要调用的API ID
     * @return 如果是则返回值为true，否则返回值为false
     * @throws UnverifiedParameterException
     */
    protected boolean apiIs(int apiId) throws UnverifiedParameterException {
        if (this.apiId == -1) {
            throw new UnverifiedParameterException("未调用validateApiParams方法对参数进行校验!");
        }
        return (this.apiId & apiId) == apiId;
    }

    /**
     * HTTPS post 请求
     *
     * @param url        请求的URL
     * @param parameters 请求的参数
     * @return 返回请求响应
     */
    protected String httpsPostReq(String url, Parameters parameters) {
        try {
            return request(url, parameters, SSH_POST);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTPS get 请求
     *
     * @param url        请求的URL
     * @param parameters 请求的参数
     * @return 返回请求响应
     */
    protected String httpsGetReq(String url, Parameters parameters) {
        try {
            return request(url, parameters, SSH_GET);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTP post 请求
     *
     * @param url        请求的URL
     * @param parameters 请求的参数
     * @return 返回请求响应
     */
    protected String postReq(String url, Parameters parameters) {
        try {
            return request(url, parameters, POST);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTP get 请求
     *
     * @param url        请求的URL
     * @param parameters 请求的参数
     * @return 返回请求的响应
     */
    protected String getReq(String url, Parameters parameters) {
        try {
            return request(url, parameters, GET);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发出一个请求
     *
     * @param url        需要请求的url
     * @param parameters 请求的参数
     * @return 返回请求的响应内容
     */
    protected String request(String url, Parameters parameters, int method) throws IOException {
        switch (method) {
            case SSH_POST:
                return NetworkKit.sshPost(url, parameters);
            case GET:
            case SSH_GET:
                return NetworkKit.get(url, parameters);
            case POST:
                return NetworkKit.post(url, parameters);
        }
        // 在任何一次请求完成后，都需要将API的调用锁锁上，防止恶意调用
        IkeChat.lock();
        return "{\"access_token\":\"asdasd\"}";
    }
}
