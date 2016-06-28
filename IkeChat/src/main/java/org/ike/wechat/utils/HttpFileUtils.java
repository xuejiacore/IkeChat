/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.utils
 * Author: Xuejia
 * Date Time: 2016/6/27 23:59
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.utils;

import com.sun.deploy.net.HttpDownload;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Class Name: HttpFileUtils
 * Create Date: 2016/6/27 23:59
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class HttpFileUtils {

    public static final int cache = 10 * 1024;
    public static final boolean isWindows;
    public static final String splash;
    public static final String root;

    static {
        if (System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows")) {
            isWindows = true;
            splash = "\\";
            root = "D:";
        } else {
            isWindows = false;
            splash = "/";
            root = "/search";
        }
    }

    /**
     * 根据url下载文件，保存到filepath中
     *
     * @param url
     * @param filepath
     * @return
     */
    public static File download(String url, String filepath) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = client.execute(httpget);

            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            if (filepath == null)
                filepath = getFilePath(response);
            File file = new File(filepath);
            file.getParentFile().mkdirs();
            FileOutputStream fileout = new FileOutputStream(file);
            /**
             * 根据实际运行效果 设置缓冲区大小
             */
            byte[] buffer = new byte[cache];
            int ch;
            while ((ch = is.read(buffer)) != -1) {
                fileout.write(buffer, 0, ch);
            }
            is.close();
            fileout.flush();
            fileout.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取response要下载的文件的默认路径
     *
     * @param response
     * @return
     */
    public static String getFilePath(HttpResponse response) {
        String filepath = root + splash;
        String filename = getFileName(response);

        if (filename != null) {
            filepath += filename;
        } else {
            filepath += getRandomFileName();
        }
        return filepath;
    }

    /**
     * 获取response header中Content-Disposition中的filename值
     *
     * @param response
     * @return
     */
    public static String getFileName(HttpResponse response) {
        Header contentHeader = response.getFirstHeader("Content-Disposition");
        String filename = null;
        if (contentHeader != null) {
            HeaderElement[] values = contentHeader.getElements();
            if (values.length == 1) {
                NameValuePair param = values[0].getParameterByName("filename");
                if (param != null) {
                    try {
                        //filename = new String(param.getValue().toString().getBytes(), "utf-8");
                        //filename=URLDecoder.decode(param.getValue(),"utf-8");
                        filename = param.getValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filename;
    }

    /**
     * 获取随机文件名
     *
     * @return
     */
    public static String getRandomFileName() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static void outHeaders(HttpResponse response) {
        Header[] headers = response.getAllHeaders();
        for (int i = 0; i < headers.length; i++) {
            System.out.println(headers[i]);
        }
    }

    public static void main(String[] args) {
        String filepath = "D:\\test\\a.jpg";
        HttpFileUtils.download("https://api.weixin.qq.com/cgi-bin/media/get?access_token=k1xWFs3vaPWrYE1CNbPuvEthdGLhwzea7M96Ondytaos_JOHDeOyrBnNOYoN_jfYjjcIcVtavzZHeUro70azcL83KgtaKQHgeFkUxmkA4WDt4pI7Lmr4xyh8EpGxeZETKVUgAIABML&media_id=ZheZ_0DSB6fD4c3QenSZlmzCj7JpTqULGIJ6moGAumDZp7Q45hRlzSQIX9bA1IWM", filepath);
    }
}
