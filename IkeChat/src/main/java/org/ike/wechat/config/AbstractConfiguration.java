/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/18 22:06
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.config;

import org.apache.log4j.Logger;
import org.ike.wechat.cache.IStorage;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.auth.AuthorInfo;
import org.ike.wechat.exception.InvalidateAPIException;
import org.ike.wechat.exception.InvalidateParametersException;

/**
 * Class Name: AbstractConfiguration
 * Create Date: 2016/6/18 22:06
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public abstract class AbstractConfiguration implements IConfiguration {

    static Logger logger = Logger.getLogger(IkeChat.LOGGER_NAME);

    /**
     * 授权的存储接口
     */
    private static IStorage storageProcessor = null;

    protected AuthorInfo authorInfo = new AuthorInfo();

    /**
     * API初始化自动从配置路径中加载当前有效的凭证，如果凭证的超过凭证的有效期，那么将自动进行刷新操作
     */

    public AbstractConfiguration() {
        storageProcessor = initStorageProcessor();
        if (storageProcessor != null) {
            authorInfo = storageProcessor.onTokenRead();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                // TODO：AuthorInfo 需要进行硬盘存储，每次从硬盘中读取上次的时间以及各种信息，可以使用Gson将信息写到配置文件中
                logger.debug("微信API退出，进行授权信息的保存操作");
                if (storageProcessor != null) {
                    storageProcessor.onTokenSave(authorInfo);
                }
            }
        }));
    }

    /**
     * 主动刷新凭证
     */
    private static void autoRefreshToken() {
        try {
            IkeChat.req(IkeChat.API_REFRESH_TOKEN, new Object[][]{IkeChat.PARAM_RELEASE_LOCKER});
        } catch (InvalidateParametersException e) {
            e.printStackTrace();
        } catch (InvalidateAPIException e) {
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

        return true;
    }

    public abstract IStorage initStorageProcessor();

}
