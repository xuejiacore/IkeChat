/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.auth
 * Author: Xuejia
 * Date Time: 2016/6/11 20:20
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.ike.wechat.core.auth;

/**
 * Class Name: AuthorInfo
 * Create Date: 2016/6/11 20:20
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 授权信息
 */
public class AuthorInfo {
    /**
     * 基础 access token
     */
    private static String accessToken = null;
    /**
     * access token 的超时时长
     */
    private static int accessTokenExpireIn = Integer.MAX_VALUE;
    /**
     * js api 调用所需的Ticket
     */
    private static String jsApiTicket = null;
    /**
     * js api 的超时时长
     */
    private static int jsApiTokenExpireIn = Integer.MAX_VALUE;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                // TODO：AuthorInfo 需要进行硬盘存储，每次从硬盘中读取上次的时间以及各种信息，可以使用Gson将信息写到配置文件中
                AuthorInfo.saveToken2Disk();
            }
        }));
    }

    /**
     * 将授权信息保存到硬盘中
     * <p/>
     * 在发生api异常的时候也会调用本方法进行授权信息硬存储
     *
     * @return 如果保存成功，那么返回值为true，否则返回值为false
     */
    public static boolean saveToken2Disk() {
        System.err.println("微信API退出，进行授权信息保存操作");
        return true;
    }
    // TODO：凡是对授权凭证进行更新的操作，如果不指定强制执行，那么抛出一个非法操作异常
}
