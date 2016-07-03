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
import org.ike.wechat.core.user.bean.UserGroup;
import org.ike.wechat.exception.ChatException;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.parser.*;

import java.io.IOException;
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
 */
public class UserAPI extends AbstractApi {

    private static final String CGI_CREATE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=%s";          // 创建用户分组
    private static final String CGI_QUERY_USER_GROUPS = "https://api.weixin.qq.com/cgi-bin/groups/get";             // 查询用户分组
    private static final String CGI_QUERY_USER_GROUP_IN = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=%s";         // 查询用户所在的分组
    private static final String CGI_MODIFY_GROUP_NAME = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=%s";          // 修改分组名
    private static final String CGI_MOVE_USER_2_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=%s";  // 移动用户分组
    private static final String CGI_MOVE_USER_2_GROUP_BAT = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=%s";// 批量移动用户分组
    private static final String CGI_DELETE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=%s";          // 删除分组
    private static final String CGI_CREATE_USER_TAG = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=%s";              // 创建用户标签
    private static final String CGI_FETCH_TAGS = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=%s";                      // 获取已经创建的标签
    private static final String CGI_EDIT_TAG = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=%s";                     // 编辑标签
    private static final String CGI_DELETE_TAG = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=%s";                   // 删除标签
    private static final String CGI_FETCH_FANS_BY_TAG = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=%s";           // 获取标签下的粉丝列表
    private static final String CGI_TAG_2_USER_BAT = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=%s"; // 批量为用户打标签
    private static final String CGI_RM_TAG_BAT = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=%s";   // 批量为用户取消标签
    private static final String CGI_FETCH_USER_TAGS = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=%s";           // 获取用户身上的所有标签
    private static final String CGI_SET_USER_REMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=%s";   // 设置用户的备注
    private static final String CGI_FETCH_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=%s";// 获取用户的基本信息
    private static final String CGI_FETCH_USER_LIST = "https://api.weixin.qq.com/cgi-bin/user/get";  // 获取用户列表

    public IParameterKey[] getNecessaryParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_UG_CREATE_USER_GROUP)) {
                return new IParameterKey[]{new ParameterKey("group", "用户组实体")};
            } else if (apiIs(IkeChat.API_UG_QUERY_USER_GROUP_IN) || apiIs(IkeChat.API_UT_FETCH_USER_TAGS) || apiIs(IkeChat.API_UI_FETCH_USER_INFO)) {
                return new IParameterKey[]{new ParameterKey("openid", "用户的openid")};
            } else if (apiIs(IkeChat.API_UG_MODIFY_GROUP_NAME)) {
                return new IParameterKey[]{new ParameterKey("id", "分组Id，由微信分配"), new ParameterKey("name", "分组名字（30个字符以内）")};
            } else if (apiIs(IkeChat.API_UG_MOVE_USER_2_GROUP)) {
                return new IParameterKey[]{new ParameterKey("openid", "用户的openid"), new ParameterKey("to_groupid", "分组id")};
            } else if (apiIs(IkeChat.API_UG_MOVE_USER_2_GROUP_BAT)) {
                return new IParameterKey[]{new ParameterKey("openid_list", "需要移动的用户openid数组"), new ParameterKey("to_groupid", "分组id")};
            } else if (apiIs(IkeChat.API_UG_DELETE_USER_GROUP)) {
                return new IParameterKey[]{new ParameterKey("id", "分组id")};
            } else if (apiIs(IkeChat.API_UT_CREATE_USER_TAG)) {
                return new IParameterKey[]{new ParameterKey("name", "标签的名称")};
            } else if (apiIs(IkeChat.API_UT_EDIT_TAG)) {
                return new IParameterKey[]{new ParameterKey("name", "修改后的标签名称"), new ParameterKey("id", "标签ID")};
            } else if (apiIs(IkeChat.API_UT_DELETE_TAG)) {
                return new IParameterKey[]{new ParameterKey("id", "标签ID")};
            } else if (apiIs(IkeChat.API_UT_FETCH_FANS_BY_TAG)) {
                return new IParameterKey[]{new ParameterKey("tagid", "标签ID")};
            } else if (apiIs(IkeChat.API_UT_TAG_2_USER_BAT) || apiIs(IkeChat.API_UT_RM_TAG_BAT)) {
                return new IParameterKey[]{new ParameterKey("openid_list", "粉丝列表"), new ParameterKey("tagid", "标签ID")};
            } else if (apiIs(IkeChat.API_UR_SET_USER_REMARK)) {
                return new IParameterKey[]{new ParameterKey("openid", "用户的openid"), new ParameterKey("remark", "备注名称")};
            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        }
        return new IParameterKey[0];
    }

    public IParameterKey[] getOptionalParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_UG_CREATE_USER_GROUP) || apiIs(IkeChat.API_UI_FETCH_USER_LIST)) {
                return new IParameterKey[]{new ParameterKey("next_openid", "第一个拉取的OPENID，不填默认从头开始拉取")};
            } else if (apiIs(IkeChat.API_UI_FETCH_USER_INFO)) {
                return new IParameterKey[]{new ParameterKey("lang", "国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语")};
            }
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        }
        return new IParameterKey[0];
    }

    public Response req(int apiId, Parameters parameters) {
        try {
            parameters.put(new ParameterKey("access_token"), new ParameterValue(IkeChat.getAuthorInfo().getAccessToken()));

            if (apiIs(IkeChat.API_UG_CREATE_USER_GROUP)) {
                // 创建用户分组
                HashMap<String, UserGroup> group = new HashMap<String, UserGroup>();
                group.put("group", (UserGroup) parameters.get("group").getValue());
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_CREATE_USER_GROUP, IkeChat.getAuthorInfo().getAccessToken()), new Gson().toJson(group)));

            } else if (apiIs(IkeChat.API_UG_QUERY_USER_GROUPS)) {
                // 获取分组信息
                return new Response(apiId, httpsPostReq(CGI_QUERY_USER_GROUPS, parameters));

            } else if (apiIs(IkeChat.API_UG_QUERY_USER_GROUP_IN)) {
                // 根据用户的openId获取用户的分组信息
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_QUERY_USER_GROUP_IN, IkeChat.getAuthorInfo().getAccessToken()), "{\"openid\":\"" + parameters.get("openid").getValue() + "\"}"));

            } else if (apiIs(IkeChat.API_UG_MODIFY_GROUP_NAME)) {
                // 修改分组
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_MODIFY_GROUP_NAME, IkeChat.getAuthorInfo().getAccessToken()), "{\"group\":{\"id\":" + parameters.get("id").getValue() + ", \"name\":\"" + parameters.get("name").getValue() + "\"}"));

            } else if (apiIs(IkeChat.API_UG_MOVE_USER_2_GROUP)) {
                // 将一个用户移动到另外一个分组
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_MOVE_USER_2_GROUP, IkeChat.getAuthorInfo().getAccessToken()), "{\"openid\":\"" + parameters.get("openid").getValue() + "\", \"to_groupid\":" + parameters.get("to_groupid").getValue() + "}"));

            } else if (apiIs(IkeChat.API_UG_MOVE_USER_2_GROUP_BAT)) {
                // 批量移动用户到另外一个分组
                HashMap<String, Object> postData = new HashMap<String, Object>();
                postData.put("openid_list", parameters.get("openid_list").getValue());
                postData.put("to_groupid", parameters.get("to_groupid").getValue());
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_MOVE_USER_2_GROUP_BAT, IkeChat.getAuthorInfo().getAccessToken()), new Gson().toJson(postData)));

            } else if (apiIs(IkeChat.API_UG_DELETE_USER_GROUP)) {
                // 根据分组ID删除用户分组
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_DELETE_USER_GROUP, IkeChat.getAuthorInfo().getAccessToken()), "{\"group\":{\"id\":" + parameters.get("id").getValue() + "}}"));

            } else if (apiIs(IkeChat.API_UT_CREATE_USER_TAG)) {
                // 创建一个用户标签
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_CREATE_USER_TAG, IkeChat.getAuthorInfo().getAccessToken()), "{\"tag\":{\"name\":\"" + parameters.get("name").getValue() + "\"}}"));

            } else if (apiIs(IkeChat.API_UT_FETCH_TAGS)) {
                // 获得已经创建的用户标签
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_FETCH_TAGS, IkeChat.getAuthorInfo().getAccessToken()), ""));

            } else if (apiIs(IkeChat.API_UT_EDIT_TAG)) {
                // 编辑用户标签
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_EDIT_TAG, IkeChat.getAuthorInfo().getAccessToken()), "{\"tag\":{\"id\":" + parameters.get("id").getValue() + ", \"name\":\"" + parameters.get("name").getValue() + "\"}}"));

            } else if (apiIs(IkeChat.API_UT_DELETE_TAG)) {
                // 删除用户标签
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_DELETE_TAG, IkeChat.getAuthorInfo().getAccessToken()), "{\"tag\":{\"id\":" + parameters.get("id").getValue() + "}}"));

            } else if (apiIs(IkeChat.API_UT_FETCH_FANS_BY_TAG)) {
                // 根据标签获取用户列表
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_FETCH_FANS_BY_TAG, IkeChat.getAuthorInfo().getAccessToken()), "{\"tagid\":" + parameters.get("tagid").getValue() + ", \"next_openid\":\"" + parameters.get("next_openid").getValue() + "\"}"));

            } else if (apiIs(IkeChat.API_UT_TAG_2_USER_BAT) || apiIs(IkeChat.API_UT_RM_TAG_BAT)) {
                // 批量为用户设置标签
                HashMap<String, Object> postData = new HashMap<String, Object>();
                postData.put("openid_list", parameters.get("openid_list").getValue());
                postData.put("tagid", parameters.get("tagid").getValue());
                return new Response(apiId, httpsJsonPostReq(String.format(apiIs(IkeChat.API_UT_TAG_2_USER_BAT) ? CGI_TAG_2_USER_BAT : CGI_RM_TAG_BAT, IkeChat.getAuthorInfo().getAccessToken()), new Gson().toJson(postData)));

            } else if (apiIs(IkeChat.API_UT_FETCH_USER_TAGS)) {
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_FETCH_USER_TAGS, IkeChat.getAuthorInfo().getAccessToken()), "{\"openid\":\"" + parameters.get("openid").getValue() + "\"}"));

            } else if (apiIs(IkeChat.API_UR_SET_USER_REMARK)) {
                return new Response(apiId, httpsJsonPostReq(String.format(CGI_SET_USER_REMARK, IkeChat.getAuthorInfo().getAccessToken()), "{\"openid\":\"" + parameters.get("openid").getValue() + "\", \"remark\":\"" + parameters.get("remark").getValue() + "\"}"));

            } else if (apiIs(IkeChat.API_UI_FETCH_USER_INFO)) {
                return new Response(apiId, httpsPostReq(String.format(CGI_FETCH_USER_INFO, IkeChat.getAuthorInfo().getAccessToken(), parameters.get("openid").getValue(), parameters.getOrDef("lang", "zh_CN")), parameters));

            } else if (apiIs(IkeChat.API_UI_FETCH_USER_LIST)) {
                return new Response(apiId, httpsPostReq(CGI_FETCH_USER_LIST, parameters));

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
//
//        System.err.println(IkeChat.req(IkeChat.API_UG_QUERY_USER_GROUPS, IkeChat.PARAM_EMPTY));
//        System.err.println(IkeChat.req(IkeChat.API_UG_QUERY_USER_GROUP_IN, new Object[][]{}));
//        System.err.println(IkeChat.req(IkeChat.API_UG_MODIFY_GROUP_NAME, new Object[][]{{"id", 103}, {"name", "修改的名称2"}}));
//        System.err.println(IkeChat.req(IkeChat.API_UG_MOVE_USER_2_GROUP, new Object[][]{{"openid", "oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk"}, {"to_groupid", 103}}));
//        System.err.println(IkeChat.req(IkeChat.API_UG_MOVE_USER_2_GROUP_BAT, new Object[][]{{"openid_list",
//                new String[]{"oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk", "oAm5Vt9rxcMzf-UZBHtyNq994qhg"}}, {"to_groupid", 102}}));
//        System.err.println(IkeChat.req(IkeChat.API_UT_CREATE_USER_TAG, new Object[][]{{"name", "测试标签名称"}}));
//        System.err.println(IkeChat.req(IkeChat.API_UT_FETCH_TAGS, IkeChat.PARAM_EMPTY));
//        System.err.println(IkeChat.req(IkeChat.API_UT_EDIT_TAG, new Object[][]{{"id", 103}, {"name", "修改后的标签名称"}}));
//        System.err.println(IkeChat.req(IkeChat.API_UT_DELETE_TAG, new Object[][]{{"id", 103}}));
//        System.err.println(IkeChat.req(IkeChat.API_UT_FETCH_FANS_BY_TAG, new Object[][]{{"tagid", 102}}));
//        System.err.println(IkeChat.req(IkeChat.API_UT_TAG_2_USER_BAT, new Object[][]{{"openid_list",
//                new String[]{"oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk", "oAm5Vt9rxcMzf-UZBHtyNq994qhg"}}, {"tagid", "102"}}));
//        System.err.println(IkeChat.req(IkeChat.API_UT_FETCH_USER_TAGS, new Object[][]{{"openid", "oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk"}}));
//        System.err.println(IkeChat.req(IkeChat.API_UR_SET_USER_REMARK, new Object[][]{{"openid", "oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk"}, {"remark", "测试备注"}}));
//        System.err.println(IkeChat.req(IkeChat.API_UI_FETCH_USER_INFO, new Object[][]{{"openid", "oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk"}, {"lang", "zh_CN"}}));
        System.err.println(IkeChat.req(IkeChat.API_UI_FETCH_USER_LIST, IkeChat.PARAM_EMPTY));

    }
}
