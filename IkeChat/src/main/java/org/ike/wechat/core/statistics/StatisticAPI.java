/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.statistics
 * Author: Xuejia
 * Date Time: 2016/6/29 8:54
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.statistics;

import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.exception.ChatException;
import org.ike.wechat.exception.UnverifiedParameterException;
import org.ike.wechat.parser.IParameterKey;
import org.ike.wechat.parser.ParameterKey;
import org.ike.wechat.parser.Parameters;
import org.ike.wechat.parser.Response;

import java.io.IOException;

/**
 * Class Name: StatisticAPI
 * Create Date: 2016/6/29 8:54
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 * <p/>
 * user_source	用户的渠道，数值代表的含义如下：
 * <p/>
 * 0代表其他合计 1代表公众号搜索 17代表名片分享 30代表扫描二维码 43代表图文页右上角菜单
 * 51代表支付后关注（在支付完成页） 57代表图文页内公众号名称 75代表公众号文章广告 78代表朋友圈广告
 */
public class StatisticAPI extends AbstractApi {

    private static final String CGI_GET_USER_SUMMARY = "https://api.weixin.qq.com/datacube/getusersummary?access_token=%s";                 // 获取用户增减数据 [跨度7]
    private static final String CGI_GET_USER_CUMULATE = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=%s";               // 获得累计用户数据 [跨度7]
    private static final String CGI_GET_ARTICLESUMMARY = "https://api.weixin.qq.com/datacube/getarticlesummary?access_token=%s";            // 获取图文群发每日数据 [跨度1]
    private static final String CGI_GET_ARTICLETOTAL = "https://api.weixin.qq.com/datacube/getarticletotal?access_token=%s";                // 获取图文群发总数据 [跨度1]
    private static final String CGI_GET_USER_READ = "https://api.weixin.qq.com/datacube/getuserread?access_token=%s";                       // 获取图文统计数据 [跨度3]
    private static final String CGI_GET_USER_READHOUR = "https://api.weixin.qq.com/datacube/getuserreadhour?access_token=%s";               // 获取图文统计分时数据 [跨度1]
    private static final String CGI_GET_USER_SHARE = "https://api.weixin.qq.com/datacube/getusershare?access_token=%s";                     // 获取图文分享转发数据 [跨度7]
    private static final String CGI_GET_USER_SHAREHOUR = "https://api.weixin.qq.com/datacube/getusersharehour?access_token=%s";             // 获取图文分享转发分时数据 [跨度1]

    public IParameterKey[] getNecessaryParams(int apiId) {
        try {
            if (apiIs(IkeChat.API_DA_USER_SUMMARY)) {
                return new IParameterKey[]{
                        new ParameterKey("begin_date", "获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”" +
                                "（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错"),
                        new ParameterKey("end_date", "获取数据的结束日期，end_date允许设置的最大值为昨日")
                };
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
            String statisticCGI = null;
            if (apiIs(IkeChat.API_DA_USER_SUMMARY)) {
                statisticCGI = CGI_GET_USER_SUMMARY;
            } else if (apiIs(IkeChat.API_DA_USER_CUMULATE)) {
                statisticCGI = CGI_GET_USER_CUMULATE;
            } else if (apiIs(IkeChat.API_DA_GET_ARTICLESUMMARY)) {
                statisticCGI = CGI_GET_ARTICLESUMMARY;
            } else if (apiIs(IkeChat.API_DA_GET_ARTICLETOTAL)) {
                statisticCGI = CGI_GET_ARTICLETOTAL;
            } else if (apiIs(IkeChat.API_DA_GET_USER_READ)) {
                statisticCGI = CGI_GET_USER_READ;
            } else if (apiIs(IkeChat.API_DA_GET_USER_READHOUR)) {
                statisticCGI = CGI_GET_USER_READHOUR;
            } else if (apiIs(IkeChat.API_DA_GET_USER_SHARE)) {
                statisticCGI = CGI_GET_USER_SHARE;
            } else if (apiIs(IkeChat.API_DA_GET_USER_SHAREHOUR)) {
                statisticCGI = CGI_GET_USER_SHAREHOUR;
            }
            if (statisticCGI == null) {
                return null;
            }
            return new Response(apiId, httpsJsonPostReq(
                    String.format(statisticCGI, IkeChat.getAuthorInfo().getAccessToken()),
                    "{\"begin_date\":\"" + parameters.get("begin_date").getValue() +
                            "\", \"end_date\":\"" + parameters.get("end_date").getValue() + "\"}"));
        } catch (UnverifiedParameterException e) {
            e.printStackTrace();
        } catch (ChatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ChatException {
        IkeChat.loadConfiguration(new DefaultConfiguration());
//        System.err.println(IkeChat.req(IkeChat.API_DA_USER_SUMMARY, new Object[][]{{"begin_date", "2016-06-25"}, {"end_date", "2016-06-28"}}));
//        System.err.println(IkeChat.req(IkeChat.API_DA_USER_CUMULATE, new Object[][]{{"begin_date", "2016-06-25"}, {"end_date", "2016-06-28"}}));
//        System.err.println(IkeChat.req(IkeChat.API_DA_GET_ARTICLESUMMARY, new Object[][]{{"begin_date", "2016-06-28"}, {"end_date", "2016-06-28"}}));
//        System.err.println(IkeChat.req(IkeChat.API_DA_GET_ARTICLETOTAL, new Object[][]{{"begin_date", "2016-06-28"}, {"end_date", "2016-06-28"}}));
//        System.err.println(IkeChat.req(IkeChat.API_DA_GET_USER_READ, new Object[][]{{"begin_date", "2016-06-26"}, {"end_date", "2016-06-28"}}));
//        System.err.println(IkeChat.req(IkeChat.API_DA_GET_USER_READHOUR, new Object[][]{{"begin_date", "2016-06-28"}, {"end_date", "2016-06-28"}}));
//        System.err.println(IkeChat.req(IkeChat.API_DA_GET_USER_SHARE, new Object[][]{{"begin_date", "2016-06-24"}, {"end_date", "2016-06-28"}}));
//        System.err.println(IkeChat.req(IkeChat.API_DA_GET_USER_SHAREHOUR, new Object[][]{{"begin_date", "2016-06-28"}, {"end_date", "2016-06-28"}}));
    }
}
