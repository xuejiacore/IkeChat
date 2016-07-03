/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.config
 * Author: Xuejia
 * Date Time: 2016/7/2 11:34
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.config;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.ike.wechat.cache.ICache;
import org.ike.wechat.exception.LoadingConfigException;
import org.ike.wechat.log.IResponseListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class Name: Configuration
 * Create Date: 2016/7/2 11:34
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class Configuration {
    Logger logger = Logger.getLogger(Configuration.class);
    private String configPath = null;
    private Element root = null;

    private static String appid = null;
    private static String appsecret = null;
    private static String token = null;
    private static ICache cache = null;
    private static IResponseListener responseListener = null;

    public Configuration(String configPath) {
        this.configPath = configPath;
    }

    public boolean loadConfig() throws LoadingConfigException {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.configPath);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(fileInputStream);
            root = document.getRootElement();
        } catch (FileNotFoundException e) {
            throw new LoadingConfigException("配置文件读取异常，请检查配置路径", e);
        } catch (DocumentException e) {
            throw new LoadingConfigException("配置文件解析错误，请检查配置文件是否合法", e);
        }
        logger.debug("配置文件读取完成，正在解析...");
        if (this.loadBase()) {
            try {
                loadCache();
                loadResponseListener();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private boolean loadBase() {
        logger.debug("\n\n\n配置名称：" + root.element("config-name").getText());

        Element initMain = root.element("init-main");
        appid = initMain.element("appid").getText();
        appsecret = initMain.element("appsecret").getText();
        token = initMain.element("token").getText();
        logger.debug("Loading core app information>>>>>>\n" + "AppId:" + appid + "\nAppSecret:" + appsecret + "\nToken:" + token);
        return true;
    }

    private void loadCache() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Element cacheConfig = root.element("cache-config");
        if (cacheConfig != null) {
            Element cacheType = cacheConfig.element("file-path");
            if (cacheType != null) {

            }
            cache = (ICache) Class.forName(cacheConfig.elementText("cache-class")).newInstance();
            logger.debug("Loading cache system>>>>>>\nCache class:" + cache.getClass().getName());
        } else {
            logger.warn("Missing cache configuration!!>>>>>>");
        }
    }

    private void loadResponseListener() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Element responseConfig = root.element("response-filter");
        if (responseConfig != null) {
            responseListener = (IResponseListener) Class.forName(responseConfig.elementText("filter-class")).newInstance();
            logger.debug("Loading response listener>>>>>>\nListener class:" + responseListener.getClass().getName());
        } else {
            logger.warn("Missing response configuration!!>>>>>>");
        }
    }


    private boolean loadCluster() {
        return true;
    }

    public static String getAppid() {
        return appid;
    }

    public static void setAppid(String appid) {
        Configuration.appid = appid;
    }

    public static String getAppsecret() {
        return appsecret;
    }

    public static void setAppsecret(String appsecret) {
        Configuration.appsecret = appsecret;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Configuration.token = token;
    }

    public static ICache getCache() {
        return cache;
    }

    public static void setCache(ICache cache) {
        Configuration.cache = cache;
    }

    public static IResponseListener getResponseListener() {
        return responseListener;
    }

    public static void setResponseListener(IResponseListener responseListener) {
        Configuration.responseListener = responseListener;
    }
}
