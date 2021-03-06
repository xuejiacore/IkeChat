/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core
 * Author: Xuejia
 * Date Time: 2016/6/26 11:02
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.menu;

import com.google.gson.Gson;
import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.core.menu.bean.Button;
import org.ike.wechat.core.menu.bean.Menu;
import org.ike.wechat.core.menu.bean.MenuInfo;
import org.ike.wechat.core.menu.bean.selfmenu.CustomsMenuInfo;
import org.ike.wechat.exception.ChatException;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.parser.*;

import java.io.IOException;

/**
 * Class Name: MenuAPI
 * Create Date: 2016/6/26 11:02
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 菜单接口api
 */
public class MenuAPI extends AbstractApi {


    private static final String CGI_CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";      // 创建菜单
    private static final String CGI_QUERY_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get";                          // 查询菜单
    private static final String CGI_DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete";                      // 删除菜单
    private static final String CGI_QUERY_SELF_MENU = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";    // 查询自定义菜单配置

    public IParameterKey[] getNecessaryParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_MU_CREATE_MENU)) {
                return new IParameterKey[]{new ParameterKey("menu", "org.ike.wechat.core.menu.bean.Menu菜单实例")};
            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        }
        return new IParameterKey[0];
    }

    public IParameterKey[] getOptionalParams(int apiId) {
        return new IParameterKey[0];
    }

    public Response req(int apiId, Parameters parameters) {
        try {
            parameters.put(new ParameterKey("access_token"), new ParameterValue(IkeChat.getAuthorInfo().getAccessToken()));
            if (apiIs(IkeChat.API_MU_CREATE_MENU)) {
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_CREATE_MENU, IkeChat.getAuthorInfo().getAccessToken()),
                        new Gson().toJson(parameters.get("menu").getValue())));
            } else if (apiIs(IkeChat.API_MU_QUERY_MENU)) {
                return new Response(apiId, httpsPostReq(CGI_QUERY_MENU, parameters));
            } else if (apiIs(IkeChat.API_MU_DELETE_MENU)) {
                return new Response(apiId, httpsPostReq(CGI_DELETE_MENU, parameters));
            } else if (apiIs(IkeChat.API_MU_QUERY_SELF_MENU)) {
                return new Response(apiId, httpsPostReq((CGI_QUERY_SELF_MENU), parameters));
            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        } catch (ChatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ChatException, IOException {
        IkeChat.loadConfiguration(new DefaultConfiguration());

//        IkeChat.req(IkeChat.API_REFRESH_TOKEN, IkeChat.PARAM_RELEASE_LOCKER);

        Menu menu = new Menu();
        Button main1 = new Button("click", "主菜单改", "key1");
        Button main2 = new Button("click", "主菜单2", "key2");
        Button main3 = new Button("click", "主菜单3", "key3");

        Button subBtn11 = new Button("click", "子菜单11", "key11");
        Button subBtn12 = new Button("click", "子菜单12", "key12");
        main1.addSubButton(subBtn11);
        main1.addSubButton(subBtn12);

        Button subBtn21 = new Button("click", "子菜单21", "key21");
        Button subBtn22 = new Button("click", "子菜单22", "key22");
        Button subBtn23 = new Button("click", "子菜单23", "key23");
        main2.addSubButton(subBtn21);
        main2.addSubButton(subBtn22);
        main2.addSubButton(subBtn23);

        menu.getButton().add(main1);
        menu.getButton().add(main2);
        menu.getButton().add(main3);
//        System.err.println(IkeChat.req(IkeChat.API_MU_CREATE_MENU, new Object[][]{{"menu", menu}}));
//        System.err.println(IkeChat.req(IkeChat.API_MU_QUERY_MENU, IkeChat.PARAM_EMPTY).toClz(MenuInfo.class));
//        System.err.println(IkeChat.req(IkeChat.API_MU_DELETE_MENU, IkeChat.PARAM_EMPTY));
//        System.err.println(IkeChat.req(IkeChat.API_MU_QUERY_SELF_MENU, IkeChat.PARAM_EMPTY).toClz(CustomsMenuInfo.class));
    }
}
