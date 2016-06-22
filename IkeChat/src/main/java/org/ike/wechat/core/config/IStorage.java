/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/21 7:26
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

import org.ike.wechat.core.auth.AuthorInfo;

/**
 * Class Name: IStorage
 * Create Date: 2016/6/21 7:26
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 凭证存储接口
 */
public interface IStorage {
    /**
     * 保存凭证
     *
     * @return 调用存储接口进行存储
     */
    boolean onTokenSave(AuthorInfo info);

    /**
     * 读取凭证
     *
     * @return 调用读取接口进行凭证读取
     */
    AuthorInfo onTokenRead();
}
