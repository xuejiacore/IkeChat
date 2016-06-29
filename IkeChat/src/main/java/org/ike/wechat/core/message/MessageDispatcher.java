/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message
 * Author: Xuejia
 * Date Time: 2016/6/29 23:21
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.ike.wechat.core.message.listener.IMessageListener;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Name: MessageDispatcher
 * Create Date: 2016/6/29 23:21
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class MessageDispatcher {

    private IMessageListener msgListener = null;

    public MessageDispatcher(InputStream inputStream, IMessageListener listener) throws Exception {
        if (listener != null) {
            Map<String, Object> map = xmlStream2Map(inputStream);
            String msgType = (String) map.get("MsgType");
            if (msgType.equals(MsgType.MSG_TYPE_TEXT)) {
                listener.onTextMsgReceived(mapToBean(map, TextMessage.class));
            } else if (msgType.equals(MsgType.MSG_TYPE_IMAGE)) {
            } else if (msgType.equals(MsgType.MSG_TYPE_LINK)) {
            } else if (msgType.equals(MsgType.MSG_TYPE_VIDEO)) {
            } else if (msgType.equals(MsgType.MSG_TYPE_VOICE)) {
            } else if (msgType.equals(MsgType.MSG_TYPE_SHORTVIDEO)) {
            } else if (msgType.equals(MsgType.MSG_TYPE_LOCATION)) {

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

    private Map<String, Object> xmlStream2Map(InputStream inputStream) throws DocumentException {
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
    private IMessage mapToBean(Map<String, Object> map, Class<? extends IMessage> clazz) throws Exception {
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
    private Object convertValType(Object value, Class fieldTypeClass) {
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
    private Field getClassField(Class clazz, String fieldName) {
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
