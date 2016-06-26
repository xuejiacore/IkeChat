/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/18 22:06
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

import org.apache.log4j.Logger;
import org.ike.wechat.cache.ICache;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.auth.AuthorInfo;
import org.ike.wechat.exception.ChatException;
import org.ike.wechat.log.IResponseListener;

import java.io.IOException;

/**
 * Class Name: AbstractConfiguration
 * Create Date: 2016/6/18 22:06
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 抽象微信配置类
 */
public abstract class AbstractConfiguration implements IConfiguration {
    // 授权的缓存接口
    private static ICache storageProcessor = null;

    // 授权信息
    protected static AuthorInfo authorInfo = new AuthorInfo();

    /**
     * API初始化自动从配置路径中加载当前有效的凭证，如果凭证的超过凭证的有效期，那么将自动进行刷新操作
     */
    public AbstractConfiguration() {
        storageProcessor = initStorageProcessor();
        if (storageProcessor != null) {
            authorInfo = storageProcessor.onCacheLoading();
        }
        // TODO：从缓存中加载配置的时候，判断凭证是否依然有效，如果过期，那么将自动进行刷新
//        autoRefreshToken();


        // 在API退出的时候将授权凭证进行存储
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                saveToken2Disk();
            }
        }));
    }

    /**
     * 主动刷新凭证
     */
    private static void autoRefreshToken() {
        try {
            IkeChat.req(IkeChat.API_REFRESH_TOKEN, new Object[][]{IkeChat.PARAM_RELEASE_LOCKER});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ChatException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将授权信息保存到硬盘中
     * <p/>
     * 在发生api异常的时候也会调用本方法进行授权信息硬存储
     *
     * @return 如果保存成功，那么返回值为true，否则返回值为false
     */
    public static boolean saveToken2Disk() {
        Logger.getLogger(IkeChat.LOGGER_NAME).debug("微信API退出，进行授权信息的保存操作");
        if (storageProcessor != null) {
            storageProcessor.onCache(authorInfo);
        }
        return true;
    }

    /**
     * 配置类需要实现该抽象方法，用于对凭证信息的存取缓存实现
     *
     * @return 返回一个实现了缓存接口的存取实例
     */
    public abstract ICache initStorageProcessor();
}
