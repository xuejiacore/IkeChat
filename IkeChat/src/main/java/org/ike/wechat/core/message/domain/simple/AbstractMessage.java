/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message.domain.simple
 * Author: Xuejia
 * Date Time: 2016/6/30 18:19
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message.domain.simple;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class Name: AbstractMessage
 * Create Date: 2016/6/30 18:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public abstract class AbstractMessage implements IMessage {
    private String ToUserName = null;
    private String FromUserName = null;
    private Long CreateTime = null;
    private String MsgType = null;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public IMessage reverse() {
        String user = ToUserName;
        ToUserName = FromUserName;
        FromUserName = user;
        CreateTime = System.currentTimeMillis();
        return this;
    }

    @SuppressWarnings("all")
    public String toXml() {
        String clzName = this.getClass().getName();
        Document document = DocumentHelper.createDocument();
        Element element = document.addElement("xml");
        List<Field> fieldList = new ArrayList<Field>();
        Class tmpClz;
        Class msgClz;
        try {
            tmpClz = Class.forName(clzName);
            msgClz = tmpClz;
            do {
                Collections.addAll(fieldList, tmpClz.getDeclaredFields());
            } while ((tmpClz = tmpClz.getSuperclass()) != null);
        } catch (ClassNotFoundException e) {
            return "success";
        }

        try {
            System.err.println(msgClz.getMethod("getContent"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        Object val = null;
        String getFieldName = null;
        Element tmpElement = null;
        for (Field field : fieldList) {
            getFieldName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                val = msgClz.getMethod(getFieldName).invoke(this);
                tmpElement = element.addElement(field.getName());
                if (String.class.getName().equals(field.getName())) {
                    tmpElement.addCDATA(val == null ? "" : val.toString());
                } else {
                    tmpElement.addText(val == null ? "" : val.toString());
                }
            } catch (IllegalAccessException e) {
                return "success";
            } catch (InvocationTargetException e) {
                return "success";
            } catch (NoSuchMethodException e) {
                return "success";
            }
        }
        return document.asXML();
    }

}
