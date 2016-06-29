/**
 * Project: IkeChat
 * Package Name: org.ike.wechat
 * Author: Xuejia
 * Date Time: 2016/6/29 19:52
 * Copyright: 2016 www.zigui.site. All rights reserved.
 **/
package org.ike.wechat;

import org.ike.wechat.core.ChatSign;
import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.core.message.IMessage;
import org.ike.wechat.core.message.MessageDispatcher;
import org.ike.wechat.core.message.TextMessage;
import org.ike.wechat.core.message.listener.TextMsgListener;

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

    static {
        //        try {
        IkeChat.loadConfiguration(new DefaultConfiguration());
//            IkeChat.req(IkeChat.API_REFRESH_TOKEN, IkeChat.PARAM_RELEASE_LOCKER);
//        } catch (ChatException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            new MessageDispatcher(req.getInputStream(), new TextMsgListener() {
                public void onTextMsgReceived(IMessage msg) {
                    TextMessage textMessage = ((TextMessage) msg);
                    System.err.println(textMessage);
                    String response = "<xml>" +
                            "<ToUserName><![CDATA[" + textMessage.getFromUserName() + "]]></ToUserName>" +
                            "<FromUserName><![CDATA[" + textMessage.getToUserName() + "]]></FromUserName>" +
                            "<CreateTime>" + System.currentTimeMillis() + "</CreateTime>" +
                            "<MsgType><![CDATA[text]]></MsgType>" +
                            "<Content><![CDATA[" + "哈哈哈哈" + "]]></Content>" +
                            "</xml>";
                    try {
                        resp.setContentType("application/xml");
                        resp.getWriter().write(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.err.println("收到消息：" + ((TextMessage) msg).getContent());
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
