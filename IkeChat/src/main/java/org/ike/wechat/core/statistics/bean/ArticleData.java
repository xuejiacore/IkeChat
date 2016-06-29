/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.statistics.bean
 * Author: Xuejia
 * Date Time: 2016/6/29 13:13
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.statistics.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Class Name: ArticleData
 * Create Date: 2016/6/29 13:13
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class ArticleData {
    private String ref_date = null;                                         // 数据的日期，需在begin_date和end_date之间
    private Integer ref_hour = null;                                        // 数据的小时，包括从000到2300，分别代表的是[000,100)到[2300,2400)，即每日的第1小时和最后1小时
    private Integer stat_date = null;                                       // 统计的日期，在getarticletotal接口中，ref_date指的是文章群发出日期， 而stat_date是数据统计日期
    private Integer msgid = null;                                           // 请注意：这里的msgid实际上是由msgid（图文消息id，这也就是群发接口调用后返回的msg_data_id）和index（消息次序索引）组成， 例如12003_3， 其中12003是msgid，即一次群发的消息的id； 3为index，假设该次群发的图文消息共5个文章（因为可能为多图文），3表示5个中的第3个
    private String title = null;                                            // 图文消息的标题
    private Integer int_page_read_user = null;                              // 图文页（点击群发图文卡片进入的页面）的阅读人数
    private Integer int_page_read_count = null;                             // 图文页的阅读次数
    private Integer ori_page_read_user = null;                              // 原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
    private Integer ori_page_read_count = null;                             // 原文页的阅读次数
    private Integer share_scene = null;                                     // 分享的场景 1代表好友转发 2代表朋友圈 3代表腾讯微博 255代表其他
    private Integer share_user = null;                                      // 分享的人数
    private Integer share_count = null;                                     // 分享的次数
    private Integer add_to_fav_user = null;                                 // 收藏的人数
    private Integer add_to_fav_count = null;                                // 收藏的次数
    private List<ArticleDataDetail> details = null;                         // 获取图文群发总数据接口中的详细字段解释
    private Integer target_user = null;                                     // 送达人数，一般约等于总粉丝数（需排除黑名单或其他异常情况下无法收到消息的粉丝）
    private Integer user_source = null;                                     // 在获取图文阅读分时数据时才有该字段，代表用户从哪里进入来阅读该图文。0:会话;1.好友;2.朋友圈;3.腾讯微博;4.历史消息页;5.其他

    /**
     * @return 数据的日期，需在begin_date和end_date之间
     */
    public String getRef_date() {
        return ref_date;
    }

    public void setRef_date(String ref_date) {
        this.ref_date = ref_date;
    }

    /**
     * @return 数据的小时，包括从000到2300，分别代表的是[000,100)到[2300,2400)，即每日的第1小时和最后1小时
     */
    public Integer getRef_hour() {
        return ref_hour;
    }

    public void setRef_hour(Integer ref_hour) {
        this.ref_hour = ref_hour;
    }

    /**
     * @return 统计的日期，在getarticletotal接口中，ref_date指的是文章群发出日期， 而stat_date是数据统计日期
     */
    public Integer getStat_date() {
        return stat_date;
    }

    public void setStat_date(Integer stat_date) {
        this.stat_date = stat_date;
    }

    /**
     * @return 请注意：这里的msgid实际上是由msgid（图文消息id，这也就是群发接口调用后返回的msg_data_id）和index（消息次序索引）组成， 例如12003_3， 其中12003是msgid，即一次群发的消息的id； 3为index，假设该次群发的图文消息共5个文章（因为可能为多图文），3表示5个中的第3个
     */
    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    /**
     * @return 图文消息的标题
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return 图文页（点击群发图文卡片进入的页面）的阅读人数
     */
    public Integer getInt_page_read_user() {
        return int_page_read_user;
    }

    public void setInt_page_read_user(Integer int_page_read_user) {
        this.int_page_read_user = int_page_read_user;
    }

    /**
     * @return 图文页的阅读次数
     */
    public Integer getInt_page_read_count() {
        return int_page_read_count;
    }

    public void setInt_page_read_count(Integer int_page_read_count) {
        this.int_page_read_count = int_page_read_count;
    }

    /**
     * @return 原文页（点击图文页“阅读原文”进入的页面）的阅读人数，无原文页时此处数据为0
     */
    public Integer getOri_page_read_user() {
        return ori_page_read_user;
    }

    public void setOri_page_read_user(Integer ori_page_read_user) {
        this.ori_page_read_user = ori_page_read_user;
    }

    /**
     * @return 原文页的阅读次数
     */
    public Integer getOri_page_read_count() {
        return ori_page_read_count;
    }

    public void setOri_page_read_count(Integer ori_page_read_count) {
        this.ori_page_read_count = ori_page_read_count;
    }

    /**
     * @return 分享的场景 1代表好友转发 2代表朋友圈 3代表腾讯微博 255代表其他
     */
    public Integer getShare_scene() {
        return share_scene;
    }

    public void setShare_scene(Integer share_scene) {
        this.share_scene = share_scene;
    }

    /**
     * @return 分享的人数
     */
    public Integer getShare_user() {
        return share_user;
    }

    public void setShare_user(Integer share_user) {
        this.share_user = share_user;
    }

    /**
     * @return 分享的次数
     */
    public Integer getShare_count() {
        return share_count;
    }

    public void setShare_count(Integer share_count) {
        this.share_count = share_count;
    }

    /**
     * @return 收藏的人数
     */
    public Integer getAdd_to_fav_user() {
        return add_to_fav_user;
    }

    public void setAdd_to_fav_user(Integer add_to_fav_user) {
        this.add_to_fav_user = add_to_fav_user;
    }

    /**
     * @return 收藏的次数
     */
    public Integer getAdd_to_fav_count() {
        return add_to_fav_count;
    }

    public void setAdd_to_fav_count(Integer add_to_fav_count) {
        this.add_to_fav_count = add_to_fav_count;
    }

    /**
     * @return 获取图文群发总数据接口中的详细字段解释
     */
    public List<ArticleDataDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ArticleDataDetail> details) {
        this.details = details;
    }

    /**
     * @return 送达人数，一般约等于总粉丝数（需排除黑名单或其他异常情况下无法收到消息的粉丝）
     */
    public Integer getTarget_user() {
        return target_user;
    }

    public void setTarget_user(Integer target_user) {
        this.target_user = target_user;
    }

    /**
     * @return 在获取图文阅读分时数据时才有该字段，代表用户从哪里进入来阅读该图文。0:会话;1.好友;2.朋友圈;3.腾讯微博;4.历史消息页;5.其他
     */
    public Integer getUser_source() {
        return user_source;
    }

    public void setUser_source(Integer user_source) {
        this.user_source = user_source;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
