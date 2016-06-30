/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.core.message.domain.event
 * Author: Xuejia
 * Date Time: 2016/6/30 13:33
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.core.message.domain.event;

import com.google.gson.Gson;
import org.ike.wechat.core.message.domain.simple.AbstractMessage;
import org.ike.wechat.core.message.domain.simple.IMessage;

/**
 * Class Name: ScaningQrEvent
 * Create Date: 2016/6/30 13:33
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class ScaningQrEvent extends AbstractMessage implements IMessage {

    private String Event = null;
    private Integer EventKey = null;
    private String Ticket = null;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public Integer getEventKey() {
        return EventKey;
    }

    public void setEventKey(Integer eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
