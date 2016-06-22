/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core
 * Author: Xuejia
 * Date Time: 2016/6/11 20:05
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core;

import org.apache.log4j.Logger;
import org.ike.wechat.TestAPI;
import org.ike.wechat.core.auth.AuthorInfo;
import org.ike.wechat.core.base.BaseAPI;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.core.config.IConfiguration;
import org.ike.wechat.exception.InvalidateAPIException;
import org.ike.wechat.exception.InvalidateParametersException;
import org.ike.wechat.parser.ParameterKey;
import org.ike.wechat.parser.ParameterValue;
import org.ike.wechat.parser.Parameters;
import org.ike.wechat.parser.Response;

import java.util.HashMap;

/**
 * Class Name: IkeChat
 * Create Date: 2016/6/11 20:05
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description: 微信接口调用核心类
 */
public class IkeChat {

    private static IConfiguration configuration = null;                 // 配置
    public static final Object[][] PARAM_EMPTY = new Object[0][0];     // 无参常量
    public static final Object[] PARAM_RELEASE_LOCKER = {"_release_lock", true};    // 解锁参数
    private static boolean locked = true;                         // 安全锁，防止危险的操作

    public static final String LOGGER_NAME = "_1keCh@t";                // api接口日志名
    public static final int API_USERS = 0x100;                          // 用户接口
    public static final int API_SERVER = 0x200;                         // 服务器接口
    public static final int API_MENU = 0X400;                           // 菜单接口
    public static final int API_MESSAGE = 0X800;                        // 消息接口
    public static final int API_WEB = 0X1000;                           // 网页接口
    public static final int API_MATERIAL = 0X2000;                      // 素材管理接口
    public static final int API_ACCOUNT = 0X4000;                       // 账号接口
    public static final int API_DATA = 0X8000;                          // 数据统计
    public static final int API_COUPONS = 0X10000;                      // 卡券接口
    public static final int API_STORE = 0X20000;                        // 门店接口
    public static final int API_DEVICE = 0X40000;                       // 设备接口
    public static final int API_CUSTOMER_SERV = 0X80000;                // 客服接口
    public static final int API_SHAKING = 0X100000;                     // 微信摇一摇接口
    public static final int API_WIFI = 0X200000;                        // 微信wifi接口
    public static final int API_SCANNING = 0X400000;                    // 微信扫描接口
    public static final int API_15 = 0X800000;
    public static final int API_16 = 0X1000000;
    public static final int API_17 = 0X2000000;
    public static final int API_18 = 0X4000000;
    public static final int API_BASE = 0X8000000;                       // 基础接口

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static final String P_APP_ID = "appID";                       // AppId
    public static final String P_SECRET = "appsecret";                  // AppSecret
    public static final String P_SECRET_KEY_PATH = "secretPath";         // SecretKey Path
    public static final String P_DISK_ENCRYPT = "diskEncrypt";          // diskEncrypt
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    static Logger logger = Logger.getLogger(IkeChat.LOGGER_NAME);
    private static HashMap<Integer, Class<? extends IAPI>> apiMapper = new HashMap<Integer, Class<? extends IAPI>>();

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ****** TODO：对话服务接口开始-->
    // 基础支持
    /**
     * 刷新基础access token，无参数，返回json
     * DOCUMENT：http://mp.weixin.qq.com/wiki/14/9f9c82c1af308e3b14ba9b973f99a8ba.html
     */
    public static final int API_REFRESH_TOKEN = API_BASE | 0x01;
    /**
     * 获取服务器IP列表，无参数，返回json
     * DOCUMENT：http://mp.weixin.qq.com/wiki/4/41ef0843d6e108cf6b5649480207561c.html
     */
    public static final int API_LIST_SERVER_IPS = API_BASE | 0x02;

    // 接收消息
    // 发送消息
    // 用户管理
    // 推广支持
    // 界面丰富
    // 素材管理
    // ****** 对话服务接口结束

    // ****** TODO：功能服务接口开始-->
    // 智能接口
    // 设备功能
    // 多客服
    // ****** 功能服务接口结束

    // ****** TODO：网页服务接口开始-->
    // 网页账号
    // 基础接口
    // 分享接口
    // 图像接口
    // 音频接口
    // 智能接口
    // 设备信息
    // 地理位置
    // 界面操作
    // ****** 网页服务接口结束
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    static {
        IkeChat.apiMapper.put(API_USERS, TestAPI.class);
        IkeChat.apiMapper.put(API_SERVER, TestAPI.class);
        IkeChat.apiMapper.put(API_USERS, TestAPI.class);
        IkeChat.apiMapper.put(API_SERVER, TestAPI.class);
        IkeChat.apiMapper.put(API_MENU, TestAPI.class);
        IkeChat.apiMapper.put(API_MESSAGE, TestAPI.class);
        IkeChat.apiMapper.put(API_WEB, TestAPI.class);
        IkeChat.apiMapper.put(API_MATERIAL, TestAPI.class);
        IkeChat.apiMapper.put(API_ACCOUNT, TestAPI.class);
        IkeChat.apiMapper.put(API_DATA, TestAPI.class);
        IkeChat.apiMapper.put(API_COUPONS, TestAPI.class);
        IkeChat.apiMapper.put(API_STORE, TestAPI.class);
        IkeChat.apiMapper.put(API_DEVICE, TestAPI.class);
        IkeChat.apiMapper.put(API_CUSTOMER_SERV, TestAPI.class);
        IkeChat.apiMapper.put(API_SHAKING, TestAPI.class);
        IkeChat.apiMapper.put(API_WIFI, TestAPI.class);
        IkeChat.apiMapper.put(API_SCANNING, TestAPI.class);
        IkeChat.apiMapper.put(API_BASE, BaseAPI.class);
    }

    public static synchronized void releaseLocker() {
        locked = false;
        logger.warn("警告：解锁紧缺资源!");
    }

    public static synchronized void lock() {
        locked = true;
    }

    /**
     * 获取API对应的API类
     *
     * @param apiId API的ID号
     * @return 返回API类
     */
    private static Class<? extends IAPI> obtainApiClz(int apiId) throws InvalidateAPIException {
        // 1000 0000 低八位后开始测算API类
        int cursor = 0x80;
        // [9, 20] 预留API
        int end = 0X10000000;
        while ((cursor <<= 1) != end) {
            if ((apiId & cursor) == cursor) {
                return IkeChat.apiMapper.get(cursor);
            }
        }
        throw new InvalidateAPIException("不合法的API调用");
    }

    /**
     * 加载微信配置文件
     *
     * @param conf 配置文件实体
     * @return 如果配置加载成功，那么返回值为true，否则返回值为false
     */
    public static boolean loadConfiguration(IConfiguration conf) {
        // TODO：加载微信配置文件
        IkeChat.configuration = conf;
        System.err.println(conf);
        return true;
    }

    /**
     * 重新加载配置文件
     *
     * @param conf 需要重新加载的配置文件信息
     * @return 如果重新加载成功，那么返回值为true，否则返回值为false
     */
    public static boolean reloadConfiguration(IConfiguration conf) {
        // TODO：重新加载微信配置文件
        IkeChat.configuration = conf;
        return true;
    }

    /**
     * 需要调用的API ID以及参数，发出请求并获得响应结果
     *
     * @param apiId  需要调用的API ID
     * @param params 参数
     * @return 返回响应的结果
     * @throws InvalidateParametersException
     */
    public static Response req(int apiId, Object[]... params) throws InvalidateParametersException, InvalidateAPIException {
        Class<? extends IAPI> apiClz = IkeChat.obtainApiClz(apiId);
        try {
            IAPI api = apiClz.newInstance();
            Parameters args = prepareArguments(apiId, api, params);
            return api.req(apiId, args);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new Response(null);
    }

    /**
     * 根据需要调用的API ID以及参数，发出请求并获得响应结果
     *
     * @param apiId  需要调用的API ID
     * @param params 调用参数
     * @return 返回响应的结果
     */
    public static Object req(int apiId, HashMap<Object, Object> params) {
        return null;
    }

    /**
     * 转化二维数组型参数为HashMap类型参数
     *
     * @param apiId  接口ID
     * @param api    接口
     * @param params 二维数组型参数列表
     * @return 返回包装完成的HashMap类型参数
     * @throws InvalidateParametersException 输入参数必须严格按照 param[n][2] 的数据格式输入，否则抛出异常
     */
    private static Parameters prepareArguments(int apiId, IAPI api, Object[]... params)
            throws InvalidateParametersException {

        Parameters parameters = new Parameters();

        ParameterKey parameterKey;
        for (int i = 0; i < params.length; i++) {
            if (params[i].length != 2) {
                throw new InvalidateParametersException("Please check your parameters type such as " +
                        "Object[][]:{{'key', val}[,{'key2','val2'}[,{'key3', obj}...]]}");
            } else {

                parameterKey = new ParameterKey(params[i][0]);
                if (parameters.containsKey(parameterKey)) {
                    logger.warn(String.format("警告!参数覆盖 idx:%s { \"key\": \"%s\", \"value\":\"%s\"} -> " +
                                    "idx:%s { \"key\": \"%s\", \"value\":\"%s\"}",
                            i - 1, params[i - 1][0], params[i - 1][1],
                            i, params[i][0], params[i][1]));
                }
                parameters.put(parameterKey, new ParameterValue(params[i][1]));
            }
        }
        if (((AbstractApi) api).validateApiParams(apiId, parameters)) {
            return parameters;
        }
        return null;
    }

    /**
     * 获得微信的配置
     *
     * @return 实现了IConfiguration的配置实例
     */
    public static AuthorInfo getAuthorInfo() {
        return configuration.getAuthorInfo();
    }

    public static void main(String[] args) throws InvalidateParametersException, InvalidateAPIException {
        IkeChat.loadConfiguration(new DefaultConfiguration());
        System.err.println(IkeChat.req(IkeChat.API_REFRESH_TOKEN, new Object[][]{PARAM_RELEASE_LOCKER}));

        System.err.println(IkeChat.getAuthorInfo());
    }
}