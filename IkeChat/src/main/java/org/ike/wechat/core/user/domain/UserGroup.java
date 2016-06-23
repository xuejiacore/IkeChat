/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.user.domain
 * Author: Xuejia
 * Date Time: 2016/6/22 13:24
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.user.domain;

/**
 * Class Name: UserGroup
 * Create Date: 2016/6/22 13:24
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:分组信息
 */
public class UserGroup {
    // 分组后生成的id
    private int id = 0;
    // 分组的名称 UTF-8编码
    private String name = null;

    /**
     * 分组名称
     * @param groupName 用户分组的名称
     */
    public UserGroup(String groupName) {
        this.name = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
