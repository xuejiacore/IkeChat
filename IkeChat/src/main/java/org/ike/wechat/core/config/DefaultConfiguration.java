/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/11 20:14
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

import org.ike.wechat.cache.DefaultFileCache;
import org.ike.wechat.cache.ICache;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.auth.AuthorInfo;
import org.ike.wechat.log.IResponseListener;
import org.ike.wechat.utils.PropertiesUtil;

import java.util.Date;

/**
 * Class Name: DefaultConfiguration
 * Create Date: 2016/6/11 20:14
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 微信默认配置类
 */
public class DefaultConfiguration extends AbstractConfiguration {

    public DefaultConfiguration() {
        AuthorInfo authorInfo = getAuthorInfo();
        authorInfo.setAppid(PropertiesUtil.getValue(IkeChat.P_APP_ID));
        authorInfo.setSecretKey(PropertiesUtil.getValue(IkeChat.P_SECRET));
        setAuthorInfo(authorInfo);
    }

    public IConfiguration setAuthorInfo(AuthorInfo authorInfo) {
        AbstractConfiguration.authorInfo = authorInfo;
        return this;
    }

    public AuthorInfo getAuthorInfo() {
        return authorInfo;
    }

    public IResponseListener getResponseListener() {
        return new IResponseListener() {
            public void onSuccess(int apiId, String msg, AuthorInfo authorInfo) {
                System.out.println("----------------\n" + new Date() + " - Success!\napiId :0b" +
                        Integer.toBinaryString(apiId) + "(" + apiId + ")\nmsg : " + msg + "\n" + authorInfo.getCoreInfo() + "\n----------------");
            }

            public void onFailure(int apiId, String msg, AuthorInfo authorInfo) {
                System.out.println("----------------\n" + new Date() + " - Failure!\napiId :0b" +
                        Integer.toBinaryString(apiId) + "(" + apiId + ")\nmsg : " + msg + "\n" + authorInfo.getCoreInfo() + "\n----------------");
            }
        };
    }

    @Override
    public ICache initStorageProcessor() {
        return new DefaultFileCache();
    }
}
