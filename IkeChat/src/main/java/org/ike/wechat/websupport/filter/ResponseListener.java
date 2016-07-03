/**
 * Project: IkeChat
 * Package Name: org.ike.wechat.websupport.filter
 * Author: Xuejia
 * Date Time: 2016/7/2 16:34
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.websupport.filter;

import org.ike.wechat.core.auth.AuthorInfo;
import org.ike.wechat.log.IResponseListener;

import java.util.Date;

/**
 * Class Name: ResponseListener
 * Create Date: 2016/7/2 16:34
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class ResponseListener implements IResponseListener {
    public void onSuccess(int apiId, String msg, AuthorInfo authorInfo) {
        System.out.println("----------------\n" + new Date() + " - Success!\napiId :0b" +
                Integer.toBinaryString(apiId) + "(" + apiId + ")\nmsg : " + msg + "\n" + authorInfo.getCoreInfo() + "\n----------------");
    }

    public void onFailure(int apiId, String msg, AuthorInfo authorInfo) {
        System.out.println("----------------\n" + new Date() + " - Failure!\napiId :0b" +
                Integer.toBinaryString(apiId) + "(" + apiId + ")\nmsg : " + msg + "\n" + authorInfo.getCoreInfo() + "\n----------------");
    }
}
