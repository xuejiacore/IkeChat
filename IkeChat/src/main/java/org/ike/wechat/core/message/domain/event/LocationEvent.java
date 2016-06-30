/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message.domain.event
 * Author: Xuejia
 * Date Time: 2016/6/30 13:36
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message.domain.event;

import com.google.gson.Gson;
import org.ike.wechat.core.AbstractApi;
import org.ike.wechat.core.message.domain.simple.AbstractMessage;
import org.ike.wechat.core.message.domain.simple.IMessage;

/**
 * Class Name: LocationEvent
 * Create Date: 2016/6/30 13:36
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class LocationEvent extends AbstractMessage implements IMessage {

    private String Event = null;
    private Integer Latitude = null;
    private Double Longitude = null;
    private Double Precision = null;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public Integer getLatitude() {
        return Latitude;
    }

    public void setLatitude(Integer latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getPrecision() {
        return Precision;
    }

    public void setPrecision(Double precision) {
        Precision = precision;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
