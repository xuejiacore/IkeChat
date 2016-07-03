/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.websupport.listener
 * Author: Xuejia
 * Date Time: 2016/7/2 9:55
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.websupport.listener;

import org.apache.log4j.Logger;
import org.ike.wechat.core.config.Configuration;
import org.ike.wechat.exception.LoadingConfigException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class Name: ChatCoreListener
 * Create Date: 2016/7/2 9:55
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class ChatCoreListener implements ServletContextListener {

    Logger logger = Logger.getLogger(ChatCoreListener.class);
    // 获取配置类

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String configPathVal = context.getInitParameter("ChatConfigPath");
        if (configPathVal.startsWith("classpath:")) {
            configPathVal = "/WEB-INF/classes/" + configPathVal.substring(configPathVal.substring(10).startsWith("/") ? 11 : 10);
        }
        String realChatCfgPath = context.getRealPath(configPathVal);

        // TODO:读取配置文件中指定的微信配置文件
        Configuration configuration = new Configuration(realChatCfgPath);
        try {
            if (!configuration.loadConfig()) {
                System.err.println("配置读取异常");
            }
        } catch (LoadingConfigException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
