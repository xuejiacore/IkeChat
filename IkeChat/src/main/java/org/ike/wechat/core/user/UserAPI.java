/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.user
 * Author: Xuejia
 * Date Time: 2016/6/22 13:18
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.user;

import com.google.gson.Gson;
import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.core.user.domain.UserGroup;
import org.ike.wechat.exception.InvalidateAPIException;
import org.ike.wechat.exception.InvalidateParametersException;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.parser.IParameterKey;
import org.ike.wechat.parser.ParameterKey;
import org.ike.wechat.parser.Parameters;
import org.ike.wechat.parser.Response;

import java.util.HashMap;

/**
 * Class Name: UserAPI
 * Create Date: 2016/6/22 13:18
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 对话服务-用户管理
 * Document:http://mp.weixin.qq.com/wiki/0/56d992c605a97245eb7e617854b169fc.html
 * <p/>
 * Remark:2016-06-23 09:15:34 用户管理封装完成
 */
public class UserAPI extends AbstractApi {

    // 创建用户分组
    private static final String CGI_CREATE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=%s";
    // 查询用户分组
    private static final String CGI_QUERY_USER_GROUPS = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=%s";
    // 查询用户所在的分组
    private static final String CGI_QUERY_USER_GROUP_IN = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=%s";
    // 修改分组名
    private static final String CGI_MODIFY_GROUP_NAME = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=%s";
    // 移动用户分组
    private static final String CGI_MOVE_USER_2_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=%s";
    // 批量移动用户分组
    private static final String CGI_MOVE_USER_2_GROUP_BAT = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=%s";
    // 删除分组
    private static final String CGI_DELETE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=%s";

    public IParameterKey[] getNecessaryParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_CREATE_USER_GROUP)) {
                return new IParameterKey[]{new ParameterKey("group", "用户组实体")};
            } else if (apiIs(IkeChat.API_QUERY_USER_GROUP_IN)) {
                return new IParameterKey[]{new ParameterKey("openid", "用户的openid")};
            } else if (apiIs(IkeChat.API_MODIFY_GROUP_NAME)) {
                return new IParameterKey[]{new ParameterKey("id", "分组Id，由微信分配"), new ParameterKey("name", "分组名字（30个字符以内）")};
            } else if (apiIs(IkeChat.API_MOVE_USER_2_GROUP)) {
                return new IParameterKey[]{new ParameterKey("openid", "用户的openid"), new ParameterKey("to_groupid", "分组id")};
            } else if (apiIs(IkeChat.API_MOVE_USER_2_GROUP_BAT)) {
                return new IParameterKey[]{new ParameterKey("openid_list", "需要移动的用户openid数组"), new ParameterKey("to_groupid", "分组id")};
            } else if (apiIs(IkeChat.API_DELETE_USER_GROUP)) {
                return new IParameterKey[]{new ParameterKey("id", "分组id")};
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
            if (apiIs(IkeChat.API_CREATE_USER_GROUP)) {
                HashMap<String, UserGroup> group = new HashMap<String, UserGroup>();
                group.put("group", (UserGroup) parameters.get("group").getValue());
                return new Response(httpsJsonPostReq(String.format(CGI_CREATE_USER_GROUP,
                        IkeChat.getAuthorInfo().getAccessToken()), new Gson().toJson(group)));
            } else if (apiIs(IkeChat.API_QUERY_USER_GROUPS)) {
                return new Response(httpsPostReq(String.format(CGI_QUERY_USER_GROUPS,
                        IkeChat.getAuthorInfo().getAccessToken()), parameters));
            } else if (apiIs(IkeChat.API_QUERY_USER_GROUP_IN)) {
                return new Response(httpsJsonPostReq(String.format(CGI_QUERY_USER_GROUP_IN, IkeChat.getAuthorInfo().getAccessToken()),
                        "{\"openid\":\"" + parameters.get("openid").getValue() + "\"}"));
            } else if (apiIs(IkeChat.API_MODIFY_GROUP_NAME)) {
                return new Response(httpsJsonPostReq(String.format(CGI_MODIFY_GROUP_NAME, IkeChat.getAuthorInfo().getAccessToken()),
                        "{\"group\":{\"id\":" + parameters.get("id").getValue() + ", \"name\":\"" + parameters.get("name").getValue() + "\"}"));
            } else if (apiIs(IkeChat.API_MOVE_USER_2_GROUP)) {
                return new Response(httpsJsonPostReq(String.format(CGI_MOVE_USER_2_GROUP, IkeChat.getAuthorInfo().getAccessToken()),
                        "{\"openid\":\"" + parameters.get("openid").getValue() + "\", \"to_groupid\":" + parameters.get("to_groupid").getValue() + "}"));
            } else if (apiIs(IkeChat.API_MOVE_USER_2_GROUP_BAT)) {
                HashMap<String, Object> postData = new HashMap<String, Object>();
                postData.put("openid_list", parameters.get("openid_list").getValue());
                postData.put("to_groupid", parameters.get("to_groupid").getValue());
                return new Response(httpsJsonPostReq(String.format(CGI_MOVE_USER_2_GROUP_BAT, IkeChat.getAuthorInfo().getAccessToken()),
                        new Gson().toJson(postData)));
            } else if (apiIs(IkeChat.API_DELETE_USER_GROUP)) {
                return new Response(httpsJsonPostReq(String.format(CGI_DELETE_USER_GROUP, IkeChat.getAuthorInfo().getAccessToken()),
                        "{\"group\":{\"id\":" + parameters.get("id").getValue() + "}}"));
            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws InvalidateParametersException, InvalidateAPIException {
        IkeChat.loadConfiguration(new DefaultConfiguration());
        IkeChat.req(IkeChat.API_REFRESH_TOKEN, IkeChat.PARAM_RELEASE_LOCKER);
//
//        System.err.println(IkeChat.req(IkeChat.API_QUERY_USER_GROUPS, IkeChat.PARAM_EMPTY));
//        System.err.println(IkeChat.req(IkeChat.API_QUERY_USER_GROUP_IN, new Object[][]{{"openid","oAm5Vt9rxcMzf-UZBHtyNq994qhg"}}));
//        System.err.println(IkeChat.req(IkeChat.API_MODIFY_GROUP_NAME, new Object[][]{{"id", 103}, {"name", "修改的名称2"}}));
//        System.err.println(IkeChat.req(IkeChat.API_MOVE_USER_2_GROUP, new Object[][]{{"openid", "oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk"}, {"to_groupid", 103}}));
//        System.err.println(IkeChat.req(IkeChat.API_MOVE_USER_2_GROUP_BAT, new Object[][]{{"openid_list",
//                new String[]{"oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk", "oAm5Vt9rxcMzf-UZBHtyNq994qhg"}}, {"to_groupid", 102}}));

        
    }
}
