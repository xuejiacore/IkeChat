/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.utils
 * Author: Xuejia
 * Date Time: 2016/6/19 9:57
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * Class Name: PropertiesUtil
 * Create Date: 2016/6/19 9:57
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 微信配置文件的读取
 */
public class PropertiesUtil {
    private static final String DEFAULT_CONFIGURATION = "/defaultChatConfig.properties";
    private static Logger logger = Logger.getLogger(PropertiesUtil.class);
    private static Properties properties = new Properties();

    static {
        InputStream configurationInput = null;
        String fileName = PropertiesUtil.class.getResource(DEFAULT_CONFIGURATION).getFile();
        URL url = Thread.currentThread().getContextClassLoader().getResource("wechat.properties");
        if (new File(fileName).exists()) {
            // 读取项目中的配置文件
            configurationInput = PropertiesUtil.class.getResourceAsStream(DEFAULT_CONFIGURATION);
            logger.debug("配置测试...");
        } else if (url != null) {
            try {
                fileName = URLDecoder.decode(url.getFile(), "UTF-8");
                fileName = fileName.replace("/", "\\");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // 读取JAR包之外的配置文件
            try {
                configurationInput = new FileInputStream(fileName.startsWith("\\") ? fileName.substring(1) : fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            logger.debug("从Jar包外读取配置文件");
        } else {
            // 从jar包中读取默认的配置文件
            configurationInput = PropertiesUtil.class.getResourceAsStream(DEFAULT_CONFIGURATION);
            logger.debug("从jar包中读取配置文件");
        }
        try {
            properties.load(configurationInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果在conf/目录下无法找到wechat.properties，那么将使用默认的配置文件
     *
     * @param key 配置文件中的键值
     * @return 返回配置的值
     */
    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
