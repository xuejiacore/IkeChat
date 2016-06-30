/**
 * Project: IkeChat
 * Package Name: org.ike.wechat
 * Author: Xuejia
 * Date Time: 2016/6/29 19:52
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat.websupport.servlet;

import org.ike.wechat.core.ChatSign;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.message.domain.event.IEvent;
import org.ike.wechat.core.message.domain.simple.IMessage;
import org.ike.wechat.core.message.domain.simple.TextMessage;
import org.ike.wechat.core.message.listener.IListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class Name: CoreServlet
 * Create Date: 2016/6/29 19:52
 * Creator: Xuejia
 * Version: v1.0
 * Updater:
 * Date Time:
 * Description:
 */
public class CoreServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            IkeChat.dispatchMsg(req.getInputStream(), new IListener() {
                public void onSubscribeListener(IEvent event) {
                    System.err.println(event);
                }

                public void onUnsubscribeListener(IEvent event) {
                    System.err.println(event);
                }

                public void onScanListener(IEvent event) {
                    System.err.println(event);
                }

                public void onLocationListener(IEvent event) {
                    System.err.println(event);
                }

                public void onClickListener(IEvent event) {
                    System.err.println(event);
                }

                public void onViewListener(IEvent event) {
                    System.err.println(event);
                }

                public void onTextMsgReceived(IMessage msg) {
                    System.err.println(msg);
                    System.err.println("收到消息：" + msg);

                    ((TextMessage) msg.reverse()).setContent("哈哈哈哈哈");
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("application/xml");
                    try {
                        resp.getWriter().write(msg.toXml());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                public void onImageMsgReceived(IMessage msg) {
                    System.err.println(msg);
                }

                public void onVoiceMsgReceived(IMessage msg) {
                    System.err.println(msg);
                }

                public void onVideoMsgReceived(IMessage msg) {
                    System.err.println(msg);
                }

                public void onShortVideoMsgReceived(IMessage msg) {
                    System.err.println(msg);
                }

                public void onLocationMsgReceived(IMessage msg) {
                    System.err.println(msg);
                }

                public void onLinkMsgReceived(IMessage msg) {
                    System.err.println(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        // 微信加密签名
        String signature = req.getParameter("signature");
        // 时间戳
        String timestamp = req.getParameter("timestamp");
        // 随机数
        String nonce = req.getParameter("nonce");
        // 随机字符串
        String echostr = req.getParameter("echostr");

        PrintWriter out = resp.getWriter();
        // 检验签名，如果一直，那么说明请求的来源是微信服务器
        if (ChatSign.checkSignature(signature, timestamp, nonce)) {
            System.out.println("请求来源是微信服务器");
            out.write(echostr);
        } else {
            System.err.println("请求的来源不是微信服务器");
        }
        out.close();
    }
}
