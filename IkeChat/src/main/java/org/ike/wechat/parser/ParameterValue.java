/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.parser
 * Author: Xuejia
 * Date Time: 2016/6/13 15:43
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.parser;

/**
 * Class Name: ParameterValue
 * Create Date: 2016/6/13 15:43
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class ParameterValue implements IParameterValue {

    private Object value = null;

    public ParameterValue(Object val) {
        this.value = val;
    }

    public Object getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }
}
