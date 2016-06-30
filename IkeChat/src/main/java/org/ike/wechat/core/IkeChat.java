/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core
 * Author: Xuejia
 * Date Time: 2016/6/11 20:05
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.ike.wechat.TestAPI;
import org.ike.wechat.core.acc.AccountAPI;
import org.ike.wechat.core.auth.AuthorInfo;
import org.ike.wechat.core.base.BaseAPI;
import org.ike.wechat.core.config.IConfiguration;
import org.ike.wechat.core.material.MaterialAPI;
import org.ike.wechat.core.menu.MenuAPI;
import org.ike.wechat.core.message.EventType;
import org.ike.wechat.core.message.MsgType;
import org.ike.wechat.core.message.domain.event.*;
import org.ike.wechat.core.message.domain.simple.*;
import org.ike.wechat.core.message.listener.IListener;
import org.ike.wechat.core.statistics.StatisticAPI;
import org.ike.wechat.core.user.UserAPI;
import org.ike.wechat.core.web.WebAPI;
import org.ike.wechat.exception.ChatException;
import org.ike.wechat.exception.InvalidateAPIException;
import org.ike.wechat.exception.InvalidateParametersException;
import org.ike.wechat.parser.ParameterKey;
import org.ike.wechat.parser.ParameterValue;
import org.ike.wechat.parser.Parameters;
import org.ike.wechat.parser.Response;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static final Object[][] PARAM_EMPTY = new Object[0][0];      // 无参常量
    public static final Object[] PARAM_RELEASE_LOCKER = {"_release_lock", true};    // 解锁参数
    private static boolean locked = true;                               // 安全锁，防止危险的操作
    public static final String LOGGER_NAME = "_1keCh@t";                // api接口日志名

    public static final int API_USERS = 0x100;                          // @ Finished 用户接口
    public static final int API_SERVER = 0x200;                         // @ Finished 服务器接口
    public static final int API_MENU = 0X400;                           // @ Finished 菜单接口
    public static final int API_MESSAGE = 0X800;                        // 消息接口
    public static final int API_WEB = 0X1000;                           // @ Finished 网页接口
    public static final int API_MATERIAL = 0X2000;                      // @ Finished 素材管理接口
    public static final int API_ACCOUNT = 0X4000;                       // @ Finished 账号接口
    public static final int API_DATA = 0X8000;                          // @ Finished 数据统计
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
    public static final int API_BASE = 0X8000000;                       // @ Finished 基础接口

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static final String P_APP_ID = "appID";                      // AppId
    public static final String P_SECRET = "appsecret";                  // AppSecret
    public static final String P_TOKEN = "token";                       // Token
    public static final String P_SECRET_KEY_PATH = "secretPath";        // SecretKey Path
    public static final String P_DISK_ENCRYPT = "diskEncrypt";          // diskEncrypt
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    static Logger logger = Logger.getLogger(IkeChat.LOGGER_NAME);
    private static HashMap<Integer, Class<? extends IAPI>> apiMapper = new HashMap<Integer, Class<? extends IAPI>>();

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ****** TODO：对话服务接口开始-->
    // 基础支持
    public static final int API_REFRESH_TOKEN = API_BASE | 0x01;                                    // 刷新AccessToken
    public static final int API_LIST_SERVER_IPS = API_BASE | 0x02;                                  // 列出服务器列表
    public static final int API_CLEAR_QUOTA = API_BASE | 0x03;                                      // 清楚接口调用频次
    public static final String API_EXCHANGE_SCENCE_QR = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s"; // 根据Ticket换取二维码
    // 接收消息
    // 发送消息
    // 用户管理
    // -->> 用户分组管理
    public static final int API_UG_CREATE_USER_GROUP = API_USERS | 0x01;                           // 创建用户分组
    public static final int API_UG_QUERY_USER_GROUPS = API_USERS | 0x02;                           // 查询分组列表
    public static final int API_UG_QUERY_USER_GROUP_IN = API_USERS | 0x03;                         // 查询用户所在分组
    public static final int API_UG_MODIFY_GROUP_NAME = API_USERS | 0x04;                           // 修改用户所在分组
    public static final int API_UG_MOVE_USER_2_GROUP = API_USERS | 0x05;                           // 单移动用户至分组
    public static final int API_UG_MOVE_USER_2_GROUP_BAT = API_USERS | 0x06;                       // 批量移动用户至分组
    public static final int API_UG_DELETE_USER_GROUP = API_USERS | 0x07;                           // 删除分组
    // -->> 用户标签管理
    public static final int API_UT_CREATE_USER_TAG = API_USERS | 0x08;                             // 创建标签(注意与分组有交集)
    public static final int API_UT_FETCH_TAGS = API_USERS | 0x09;                                  // 获取已经存在的标签
    public static final int API_UT_EDIT_TAG = API_USERS | 0x0a;                                    // 编辑标签
    public static final int API_UT_DELETE_TAG = API_USERS | 0x0b;                                  // 删除标签
    public static final int API_UT_FETCH_FANS_BY_TAG = API_USERS | 0x0c;                           // 根据标签获取粉丝列表
    public static final int API_UT_TAG_2_USER_BAT = API_USERS | 0x0d;                              // 批量为用户打标签
    public static final int API_UT_RM_TAG_BAT = API_USERS | 0x0e;                                  // 批量取消用户标签
    public static final int API_UT_FETCH_USER_TAGS = API_USERS | 0x0f;                             // 获取用户身上的所有标签
    // -->> 用户备注
    public static final int API_UR_SET_USER_REMARK = API_USERS | 0x10;                             // 为用户设置备注
    // -->> 用户信息
    public static final int API_UI_FETCH_USER_INFO = API_USERS | 0x20;                             // 获得用户的信息
    public static final int API_UI_FETCH_USER_LIST = API_USERS | 0x30;                             // 获取用户列表
    // 推广支持
    // 界面丰富
    // 素材管理
    public static final int API_MA_UPLOAD_TMP_MEDIA = API_MATERIAL | 0x01;                          // 上传临时素材
    public static final int API_MA_OBTAIN_TMP_MATERIAL_LIST = API_MATERIAL | 0x02;                  // 获得临时文件列表
    public static final int API_MA_CREATE_LIMIT_ARTICLES = API_MATERIAL | 0x03;                     // 新增永久图文
    public static final int API_MA_UPLOAD_LIMIT_MATERIAL = API_MATERIAL | 0x04;                     // 上传永久素材
    public static final int API_MA_UPLOAD_LIMIT_OTHER_MATERIAL = API_MATERIAL | 0x05;               // 上传其他类型的永久素材
    public static final int API_MA_OBTAIN_LIMIT_MATERIAL = API_MATERIAL | 0x06;                     // 获得永久素材ID
    public static final int API_MA_DELETE_LIMIT_MATERIAL = API_MATERIAL | 0x07;                     // 删除永久素材
    public static final int API_MA_MODIFY_LIMIT_MATERIAL = API_MATERIAL | 0x08;                     // 修改永久图文素材
    public static final int API_MA_CALCULATE_MATERIAL_CNT = API_MATERIAL | 0x09;                    // 查询素材总数
    public static final int API_MA_QUERY_MATERIAL_LIST = API_MATERIAL | 0x10;                       // 查询素材列表
    // 菜单管理
    public static final int API_MU_CREATE_MENU = API_MENU | 0x01;                                   // 创建菜单
    public static final int API_MU_QUERY_MENU = API_MENU | 0x02;                                    // 查询菜单
    public static final int API_MU_DELETE_MENU = API_MENU | 0x03;                                   // 删除菜单
    public static final int API_MU_QUERY_SELF_MENU = API_MENU | 0x04;                               // 查询自定义菜单
    // 账号管理
    public static final int API_AC_CREATE_QR = API_ACCOUNT | 0x01;                                  // 创建带场景值二维码
    public static final int API_LURL_2_SURL = API_ACCOUNT | 0x02;                                   // 长地址转短地址
    // 数据统计
    // -->> 用户分析数据接口
    public static final int API_DA_USER_SUMMARY = API_DATA | 0x01;                                  // 获取用户的增减数据 [跨度7]
    public static final int API_DA_USER_CUMULATE = API_DATA | 0x02;                                 // 获取累计用户数据 [跨度7]
    // -->> 图文分析数据接口
    public static final int API_DA_GET_ARTICLESUMMARY = API_DATA | 0x03;                            // 获取图文群发每日数据 [跨度1]
    public static final int API_DA_GET_ARTICLETOTAL = API_DATA | 0x04;                              // 获取图文群发总数据 [跨度1]
    public static final int API_DA_GET_USER_READ = API_DATA | 0x05;                                 // 获取图文统计数据 [跨度3]
    public static final int API_DA_GET_USER_READHOUR = API_DATA | 0x06;                             // 获取图文统计分时数据 [跨度1]
    public static final int API_DA_GET_USER_SHARE = API_DATA | 0x07;                                // 获取图文分享转发数据 [跨度7]
    public static final int API_DA_GET_USER_SHAREHOUR = API_DATA | 0x08;                            // 获取图文分享转发分时数据 [跨度1]
    // -->> 消息分析数据接口
    public static final Integer API_DA_GETUP_STREAM_MSG = API_DATA | 0x09;                          //  获取消息发送概况数据 [跨度7]
    public static final Integer API_DA_GETUP_STREAM_MSG_HOUR = API_DATA | 0x0a;                     //  获取消息分送分时数据 [跨度1]
    public static final Integer API_DA_GETUP_STREAM_MSG_WEEK = API_DATA | 0x0b;                     //  获取消息发送周数据 [跨度30]
    public static final Integer API_DA_GETUP_STREAM_MSG_MONTH = API_DATA | 0x0c;                    //  获取消息发送月数据 [跨度30]
    public static final Integer API_DA_GETUP_STREAM_MSG_DIST = API_DATA | 0x0d;                     //  获取消息发送分布数据 [跨度15]
    public static final Integer API_DA_GETUP_STREAM_MSG_DIST_WEEK = API_DATA | 0x0e;                //  获取消息发送分布周数据 [跨度30]
    public static final Integer API_DA_GETUP_STREAM_MSG_DIST_MONTH = API_DATA | 0x0f;               //  获取消息发送分布月数据 [跨度30]
    // -->> 接口分析数据接口
    public static final int API_DA_GET_INTERFACE_SUMMARY = API_DATA | 0x10;                         // 获取接口分析数据 [跨度30]
    public static final int API_DA_GET_INTERFACE_SUMMARY_HOUR = API_DATA | 0x20;                    // 获取接口分析分时数据 [跨度1]

    // ****** 对话服务接口结束

    // ****** TODO：功能服务接口开始-->
    // 智能接口
    // 设备功能
    // 多客服
    // ****** 功能服务接口结束

    // ****** TODO：网页服务接口开始-->
    // 网页账号
    public static final int API_WEB_GENERATE_REDIRECT = API_WEB | 0x01;                             // 产生重定向接口
    public static final int API_WEB_EXCHANGE_TOKEN = API_WEB | 0x02;                                // 换取网页授权access_token，同时带有openId信息
    public static final int API_WEB_REFRESH_TOKEN = API_WEB | 0x03;                                 // 刷新网页授权access_token
    public static final int API_WEB_FETCH_USER_INFO = API_WEB | 0x04;                               // 获取用户的基本信息
    public static final int API_WEB_CHECK_TOKEN = API_WEB | 0x05;                                   // 检查网页授权access_token是否有效

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
        IkeChat.apiMapper.put(API_USERS, TestAPI.class);                                                // Developed
        IkeChat.apiMapper.put(API_SERVER, TestAPI.class);                   // @ Developing
        IkeChat.apiMapper.put(API_USERS, UserAPI.class);                                                // Developed
        IkeChat.apiMapper.put(API_SERVER, TestAPI.class);                   // @ Developing
        IkeChat.apiMapper.put(API_MENU, MenuAPI.class);                                                 // Developed
        IkeChat.apiMapper.put(API_MESSAGE, TestAPI.class);                  // @ Developing
        IkeChat.apiMapper.put(API_WEB, WebAPI.class);                                                   // Developed
        IkeChat.apiMapper.put(API_MATERIAL, MaterialAPI.class);                                         // Developed
        IkeChat.apiMapper.put(API_ACCOUNT, AccountAPI.class);                                           // Developed
        IkeChat.apiMapper.put(API_DATA, StatisticAPI.class);                                            // Developed
        IkeChat.apiMapper.put(API_COUPONS, TestAPI.class);                  // @ Developing
        IkeChat.apiMapper.put(API_STORE, TestAPI.class);                    // @ Developing
        IkeChat.apiMapper.put(API_DEVICE, TestAPI.class);                   // @ Developing
        IkeChat.apiMapper.put(API_CUSTOMER_SERV, TestAPI.class);            // @ Developing
        IkeChat.apiMapper.put(API_SHAKING, TestAPI.class);                  // @ Developing
        IkeChat.apiMapper.put(API_WIFI, TestAPI.class);                     // @ Developing
        IkeChat.apiMapper.put(API_SCANNING, TestAPI.class);                 // @ Developing
        IkeChat.apiMapper.put(API_BASE, BaseAPI.class);                                                 // Developed
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
    public static Response req(int apiId, Object[]... params) throws IOException, ChatException {
        Class<? extends IAPI> apiClz;
        try {
            apiClz = IkeChat.obtainApiClz(apiId);
        } catch (InvalidateAPIException e) {
            return new Response(-99, null);
        }
        try {
            IAPI api = apiClz.newInstance();
            Parameters args = prepareArguments(apiId, api, params);
            return api.req(apiId, args);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvalidateParametersException e) {
            e.printStackTrace();
        }
        return new Response(-99, null);
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

    public static IConfiguration getConfiguration() {
        return configuration;
    }


    /**
     * 分发消息
     *
     * @param inputStream 消息输入流
     * @param listener    消息监听器
     * @throws Exception
     */
    public static void dispatchMsg(InputStream inputStream, IListener listener) throws Exception {
        if (listener != null) {
            Map<String, Object> map = xmlStream2Map(inputStream);
            String msgType = (String) map.get("MsgType");
            if ("event".equals(msgType)) {
                String eventType = (String) map.get("Event");
                if (eventType.equals(EventType.EVENT_TYPE_SUBSCRIBE)) {
                    listener.onSubscribeListener((IEvent) mapToBean(map, SubscribeEvent.class));
                } else if (eventType.equals(EventType.EVENT_TYPE_UNSUBSCRIBE)) {
                    listener.onUnsubscribeListener((IEvent) mapToBean(map, UnsubscribeEvent.class));
                } else if (eventType.equals(EventType.EVENT_TYPE_SCAN)) {
                    listener.onScanListener((IEvent) mapToBean(map, ScaningQrEvent.class));
                } else if (eventType.equals(EventType.EVENT_TYPE_LOCATION)) {
                    listener.onLocationListener((IEvent) mapToBean(map, LocationEvent.class));
                } else if (eventType.equals(EventType.EVENT_TYPE_CLICK)) {
                    listener.onClickListener((IEvent) mapToBean(map, MenuClickEvent.class));
                } else if (eventType.equals(EventType.EVENT_TYPE_VIEW)) {
                    listener.onViewListener((IEvent) mapToBean(map, MenuViewEvent.class));
                }
            } else {
                if (msgType.equals(MsgType.MSG_TYPE_TEXT)) {
                    listener.onTextMsgReceived(mapToBean(map, TextMessage.class));
                } else if (msgType.equals(MsgType.MSG_TYPE_IMAGE)) {
                    listener.onImageMsgReceived(mapToBean(map, ImageMessage.class));
                } else if (msgType.equals(MsgType.MSG_TYPE_LINK)) {
                    listener.onLinkMsgReceived(mapToBean(map, LinkMessage.class));
                } else if (msgType.equals(MsgType.MSG_TYPE_VIDEO)) {
                    listener.onVideoMsgReceived(mapToBean(map, VideoMessage.class));
                } else if (msgType.equals(MsgType.MSG_TYPE_VOICE)) {
                    listener.onVoiceMsgReceived(mapToBean(map, VoiceMessage.class));
                } else if (msgType.equals(MsgType.MSG_TYPE_SHORTVIDEO)) {
                    listener.onShortVideoMsgReceived(mapToBean(map, ShortVideoMessage.class));
                } else if (msgType.equals(MsgType.MSG_TYPE_LOCATION)) {
                    listener.onLocationMsgReceived(mapToBean(map, LocationMessage.class));
                }
            }
        }
    }

    /**
     * xml字符串转换成bean对象
     *
     * @param xmlStr xml字符串
     * @param clazz  待转换的class
     * @return 转换后的对象
     */
    public Object xmlStrToBean(String xmlStr, Class<? extends IMessage> clazz) {
        Object obj = null;
        try {
            // 将xml格式的数据转换成Map对象
            Map<String, Object> map = xmlStrToMap(xmlStr);
            //将map对象的数据转换成Bean对象
            obj = mapToBean(map, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 将xml格式的字符串转换成Map对象
     *
     * @param xmlStr xml格式的字符串
     * @return Map对象
     * @throws Exception 异常
     */
    private static Map<String, Object> xmlStrToMap(String xmlStr) throws Exception {
        if (xmlStr == null || xmlStr.length() == 0) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Document doc = DocumentHelper.parseText(xmlStr);
        Element root = doc.getRootElement();
        List children = root.elements();
        if (children != null && children.size() > 0) {
            for (Object aChildren : children) {
                Element child = (Element) aChildren;
                map.put(child.getName(), child.getTextTrim());
            }
        }
        return map;
    }

    private static Map<String, Object> xmlStream2Map(InputStream inputStream) throws DocumentException {
        Map<String, Object> map = new HashMap<String, Object>();
        Document doc = new SAXReader().read(inputStream);
        Element root = doc.getRootElement();
        List children = root.elements();
        if (children != null && children.size() > 0) {
            for (Object aChildren : children) {
                Element child = (Element) aChildren;
                map.put(child.getName(), child.getTextTrim());
            }
        }
        return map;
    }

    /**
     * 将Map对象通过反射机制转换成Bean对象
     *
     * @param map   存放数据的map对象
     * @param clazz 待转换的class
     * @return 转换后的Bean对象
     * @throws Exception 异常
     */
    private static IMessage mapToBean(Map<String, Object> map, Class<? extends IMessage> clazz) throws Exception {
        IMessage obj = clazz.newInstance();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String propertyName = entry.getKey();
                Object value = entry.getValue();
                String setMethodName = "set"
                        + propertyName.substring(0, 1).toUpperCase()
                        + propertyName.substring(1);
                Field field = getClassField(clazz, propertyName);
                Class fieldTypeClass = field != null ? field.getType() : null;
                value = convertValType(value, fieldTypeClass);
                clazz.getMethod(setMethodName, field != null ? field.getType() : null).invoke(obj, value);
            }
        }
        return obj;
    }

    /**
     * 将Object类型的值，转换成bean对象属性里对应的类型值
     *
     * @param value          Object对象值
     * @param fieldTypeClass 属性的类型
     * @return 转换后的值
     */
    private static Object convertValType(Object value, Class fieldTypeClass) {
        Object retVal;
        if (Long.class.getName().equals(fieldTypeClass.getName())
                || long.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Long.parseLong(value.toString());
        } else if (Integer.class.getName().equals(fieldTypeClass.getName())
                || int.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Integer.parseInt(value.toString());
        } else if (Float.class.getName().equals(fieldTypeClass.getName())
                || float.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Float.parseFloat(value.toString());
        } else if (Double.class.getName().equals(fieldTypeClass.getName())
                || double.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Double.parseDouble(value.toString());
        } else if (BigInteger.class.getName().equals(fieldTypeClass.getName())) {
            retVal = new BigInteger(value.toString());
        } else {
            retVal = value;
        }
        return retVal;
    }

    /**
     * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
     *
     * @param clazz     指定的class
     * @param fieldName 字段名称
     * @return Field对象
     */
    private static Field getClassField(Class clazz, String fieldName) {
        if (Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        Class superClass = clazz.getSuperclass();
        if (superClass != null) {
            return getClassField(superClass, fieldName);
        }
        return null;
    }
}