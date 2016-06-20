/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/18 22:06
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.exception.InvalidateAPIException;
import org.ike.wechat.exception.InvalidateParametersException;
import org.ike.wechat.utils.PropertiesUtil;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;

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
     * 公众号的appId
     */
    private static String appid = null;
    /**
     * 公众号的secretKey
     */
    private static String secretKey = null;
    /**
     * 基础Access Token
     */
    private static String ACCESS_TOKEN = null;
    /**
     * 基础Access Token 的有效时长
     */
    private static int ACCESS_TOKEN_EXPIRE_IN = 0;
    /**
     * JS API调用ticket
     */
    private static String JS_TICKET = null;
    /**
     * JS API调用ticket的有效时长
     */
    private static int JS_TICKET_EXPIRE_IN = 0;
    /**
     * web access token
     */
    private static String WEB_ACCESS_TOKEN = null;
    /**
     * web access token 的有效时长
     */
    private static int WEB_ACCESS_TOKEN_EXPIRE_IN = 0;

    /**
     * access token 有效状态
     */
    private static boolean isAccessTokenEffective = true;
    /**
     * js ticket 的有效状态
     */
    private static boolean isJsTicketEffective = true;
    /**
     * web token的有效状态
     */
    private static boolean isWebTokenEffective = true;

    /**
     * API初始化自动从配置路径中加载当前有效的凭证，如果凭证的超过凭证的有效期，那么将自动进行刷新操作
     */
    static {
        loadFromDisk();
        // 从硬盘中读取了配置后，对当前配置的有效性进行判断，如果已经失效，那么将自主刷新凭证
        if (!isAccessTokenEffective) {
            autoRefreshToken();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                // TODO：AuthorInfo 需要进行硬盘存储，每次从硬盘中读取上次的时间以及各种信息，可以使用Gson将信息写到配置文件中
                AbstractConfiguration.saveToken2Disk();
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
        logger.debug("微信API退出，进行授权信息的保存操作");

        HashMap<String, Object> saveInfo = new HashMap<String, Object>();
        saveInfo.put("access_token", ACCESS_TOKEN);
        saveInfo.put("access_token_expire_in", ACCESS_TOKEN_EXPIRE_IN + "");

        saveInfo.put("js_api_ticket", JS_TICKET);
        saveInfo.put("js_api_ticket_expire_in", JS_TICKET_EXPIRE_IN + "");

        saveInfo.put("web_access_token", WEB_ACCESS_TOKEN);
        saveInfo.put("js_api_ticket", WEB_ACCESS_TOKEN_EXPIRE_IN + "y");

        saveInfo.put("timestamp", System.currentTimeMillis() + "");
        saveInfo.put("state", "0");
        saveInfo.put("message", "需保存的信息");
        String info = new Gson().toJson(saveInfo);

        if ("true".equals(PropertiesUtil.getValue(IkeChat.P_DISK_ENCRYPT))) {
            byte[] byteInfo;
            try {
                byteInfo = info.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return false;
            }
            info = new BASE64Encoder().encode(byteInfo);
        }
        try {
            FileWriter fileWriter = new FileWriter(new File(PropertiesUtil.getValue(IkeChat.P_SECRET_KEY_PATH)));
            fileWriter.write(info);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 从硬盘中读取预先保存的凭证信息
     *
     * @return 如果读取成功，那么返回值为true，否则返回值为false
     */
    public static boolean loadFromDisk() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PropertiesUtil.getValue(IkeChat.P_SECRET_KEY_PATH)));
            StringBuilder sb = new StringBuilder();
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                sb.append(tmp);
            }
            if (sb.length() == 0) {
                return true;
            }
            logger.info("加载凭证：" + sb.toString());
            HashMap confMap = new Gson().fromJson(sb.toString(), HashMap.class);
            long timestamp = Long.parseLong((String) confMap.get("timestamp"));
            int state = Integer.parseInt((String) confMap.get("state"));
            String message = (String) confMap.get("message");

            // Access Token
            ACCESS_TOKEN = (String) confMap.get("access_token");
            ACCESS_TOKEN_EXPIRE_IN = (int) (Integer.parseInt((String) confMap.get("access_token_expire_in")) +
                    timestamp - System.currentTimeMillis());
            isAccessTokenEffective = ACCESS_TOKEN_EXPIRE_IN > 60;

            // Js Ticket
            JS_TICKET = (String) confMap.get("js_api_ticket");
            JS_TICKET_EXPIRE_IN = (int) (Integer.parseInt((String) confMap.get("js_api_ticket_expire_in")) +
                    timestamp - System.currentTimeMillis());
            isJsTicketEffective = JS_TICKET_EXPIRE_IN > 60;

            // Web Access Token
            WEB_ACCESS_TOKEN = (String) confMap.get("web_access_token");
            WEB_ACCESS_TOKEN_EXPIRE_IN = (int) (Integer.parseInt((String) confMap.get("js_api_ticket")) +
                    timestamp - System.currentTimeMillis());
            isWebTokenEffective = WEB_ACCESS_TOKEN_EXPIRE_IN > 60;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // TODO：凡是对授权凭证进行更新的操作，如果不指定强制执行，那么抛出一个非法操作异常
    public String getAppid() {
        return AbstractConfiguration.appid;
    }


    public void setAppid(String appid) {
        AbstractConfiguration.appid = appid;
    }

    public String getSecretKey() {
        return AbstractConfiguration.secretKey;
    }

    public void setSecretKey(String secretKey) {
        AbstractConfiguration.secretKey = secretKey;
    }

    public String getAccessToken() {
        return AbstractConfiguration.ACCESS_TOKEN;
    }

    public void setAccessToken(String accessToken) {
        AbstractConfiguration.ACCESS_TOKEN = accessToken;
    }

    public int getAccessTokenExpireIn() {
        return AbstractConfiguration.ACCESS_TOKEN_EXPIRE_IN;
    }

    public void setAccessTokenExpireIn(int accessTokenExpireIn) {
        AbstractConfiguration.ACCESS_TOKEN_EXPIRE_IN = accessTokenExpireIn;
    }

    public String getJsTicket() {
        return AbstractConfiguration.JS_TICKET;
    }

    public void setJsTicket(String jsTicket) {
        AbstractConfiguration.JS_TICKET = jsTicket;
    }

    public int getJsTicketExpireIn() {
        return AbstractConfiguration.JS_TICKET_EXPIRE_IN;
    }

    public void setJsTicketExpireIn(int jsTicketExpireIn) {
        AbstractConfiguration.JS_TICKET_EXPIRE_IN = jsTicketExpireIn;
    }

    public String getWebAccessToken() {
        return AbstractConfiguration.WEB_ACCESS_TOKEN;
    }

    public void setWebAccessToken(String webAccessToken) {
        AbstractConfiguration.WEB_ACCESS_TOKEN = webAccessToken;
    }

    public int getWebAccessTokenExpireIn() {
        return AbstractConfiguration.WEB_ACCESS_TOKEN_EXPIRE_IN;
    }

    public void setWebAccessTokenExpireIn(int webAccessTokenExpireIn) {
        AbstractConfiguration.WEB_ACCESS_TOKEN_EXPIRE_IN = webAccessTokenExpireIn;
    }

    public boolean isAccessTokenEffective() {
        return AbstractConfiguration.isAccessTokenEffective;
    }

    public void setIsAccessTokenEffective(boolean isAccessTokenEffective) {
        AbstractConfiguration.isAccessTokenEffective = isAccessTokenEffective;
    }

    public boolean isJsTicketEffective() {
        return AbstractConfiguration.isJsTicketEffective;
    }

    public void setIsJsTicketEffective(boolean isJsTicketEffective) {
        AbstractConfiguration.isJsTicketEffective = isJsTicketEffective;
    }

    public boolean isWebTokenEffective() {
        return AbstractConfiguration.isWebTokenEffective;
    }

    public void setIsWebTokenEffective(boolean isWebTokenEffective) {
        AbstractConfiguration.isWebTokenEffective = isWebTokenEffective;
    }
}
