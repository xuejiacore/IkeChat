/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.menu.bean.selfmenu
 * Author: Xuejia
 * Date Time: 2016/6/26 15:21
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.menu.bean.selfmenu;

/**
 * Class Name: Button
 * Create Date: 2016/6/26 15:21
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class Button {
    private String name;

    private String type;	//click|view|news_info|img|text|voice|video
    private String key;
    private String url;
    private String value;
    private NewsInfo news_info;

    private Button list;


    private Button sub_button;
}
