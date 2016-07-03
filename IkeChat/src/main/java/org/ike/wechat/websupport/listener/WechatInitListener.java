/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.websupport.listener
 * Author: Xuejia
 * Date Time: 2016/6/30 9:34
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.websupport.listener;

import org.apache.log4j.Logger;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.core.config.WebDefaultConfiguration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class Name: WechatInitListener
 * Create Date: 2016/6/30 9:34
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class WechatInitListener implements ServletContextListener {

    Logger logger = Logger.getLogger(WechatInitListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        IkeChat.loadConfiguration(new WebDefaultConfiguration());
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        IkeChat.getConfiguration().saveToken2Disk();
    }
}
