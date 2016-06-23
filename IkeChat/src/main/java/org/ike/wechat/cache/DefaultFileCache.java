/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/6/22 0:27
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.cache;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.auth.AuthorInfo;
import org.ike.wechat.utils.PropertiesUtil;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;

/**
 * Class Name: DefaultFileCache
 * Create Date: 2016/6/22 0:27
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:默认的凭证文件写入器
 */
public class DefaultFileCache implements ICache {
    public boolean onCache(AuthorInfo info) {
        HashMap<String, Object> saveInfo = new HashMap<String, Object>();
        saveInfo.put("access_token", info.getAccessToken());
        saveInfo.put("access_token_expire_in", info.getAccessTokenExpireIn() + "");

        saveInfo.put("js_api_ticket", info.getJsTicket());
        saveInfo.put("js_api_ticket_expire_in", info.getJsTicketExpireIn() + "");

        saveInfo.put("web_access_token", info.getWebAccessToken());
        saveInfo.put("js_api_ticket", info.getWebAccessTokenExpireIn() + "");

        saveInfo.put("timestamp", System.currentTimeMillis() + "");
        saveInfo.put("state", "0");
        saveInfo.put("message", "需保存的信息");
        String jsonData = new Gson().toJson(saveInfo);

        if ("true".equals(PropertiesUtil.getValue(IkeChat.P_DISK_ENCRYPT))) {
            byte[] byteInfo;
            try {
                byteInfo = jsonData.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return false;
            }
            jsonData = new BASE64Encoder().encode(byteInfo);
        }
        try {
            FileWriter fileWriter = new FileWriter(new File(PropertiesUtil.getValue(IkeChat.P_SECRET_KEY_PATH)));
            fileWriter.write(jsonData);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public AuthorInfo onCacheLoading() {
        AuthorInfo authorInfo = new AuthorInfo();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PropertiesUtil.getValue(IkeChat.P_SECRET_KEY_PATH)));
            StringBuilder sb = new StringBuilder();
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                sb.append(tmp);
            }
            if (sb.length() == 0) {
                return null;
            }
            Logger.getLogger(IkeChat.LOGGER_NAME).info("加载凭证：" + sb.toString());
            HashMap confMap = new Gson().fromJson(sb.toString(), HashMap.class);
            long timestamp = Long.parseLong((String) confMap.get("timestamp"));
            int state = Integer.parseInt((String) confMap.get("state"));
            String message = (String) confMap.get("message");

            // Access Token
            authorInfo.setAccessToken((String) confMap.get("access_token"));
            authorInfo.setAccessTokenExpireIn((int) (Integer.parseInt((String) confMap.get("access_token_expire_in")) +
                    timestamp - System.currentTimeMillis()));
            authorInfo.setIsAccessTokenEffective(authorInfo.getAccessTokenExpireIn() > 60);

            // Js Ticket
            authorInfo.setJsTicket((String) confMap.get("js_api_ticket"));
            authorInfo.setJsTicketExpireIn((int) (Integer.parseInt((String) confMap.get("js_api_ticket_expire_in")) +
                    timestamp - System.currentTimeMillis()));
            authorInfo.setIsJsTicketEffective(authorInfo.getJsTicketExpireIn() > 60);

            // Web Access Token
            authorInfo.setWebAccessToken((String) confMap.get("web_access_token"));
            authorInfo.setWebAccessTokenExpireIn((int) (Integer.parseInt((String) confMap.get("js_api_ticket")) +
                    timestamp - System.currentTimeMillis()));
            authorInfo.setIsWebTokenEffective(authorInfo.getWebAccessTokenExpireIn() > 60);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authorInfo;
    }
}
