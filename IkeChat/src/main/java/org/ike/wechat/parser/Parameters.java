/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.parser
 * Author: Xuejia
 * Date Time: 2016/6/13 16:04
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Name: Parameters
 * Create Date: 2016/6/13 16:04
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class Parameters extends HashMap<Object, IParameterValue> {

    /**
     * 键集合
     */
    private Set<Object> keySet = new HashSet<Object>();

    @Override
    public IParameterValue put(Object key, IParameterValue value) {
        this.keySet.add(((IParameterKey) key).getKey());
        return super.put(((IParameterKey) key).getKey(), value);
    }

    @Override
    public IParameterValue get(Object key) {
        return super.get(new ParameterKey(key).getKey());
    }

    @Override
    public boolean containsKey(Object key) {
        return this.keySet.contains(key instanceof String ? key : key.toString());
    }

}
