package org.ike.wechat.core.base;

import org.ike.wechat.core.IkeChat;
import org.ike.wechat.core.config.DefaultConfiguration;
import org.ike.wechat.exception.InvalidateAPIException;
import org.ike.wechat.exception.InvalidateParametersException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Xuejia on 2016/6/24.
 */
public class BaseAPITest {

    @Before
    public void setUp() throws Exception, InvalidateParametersException, InvalidateAPIException {
//        IkeChat.loadConfiguration(new DefaultConfiguration());
//        IkeChat.req(IkeChat.API_REFRESH_TOKEN, IkeChat.PARAM_RELEASE_LOCKER);
    }

    @Test
    public void testReq() throws Exception, InvalidateParametersException, InvalidateAPIException {
        IkeChat.loadConfiguration(new DefaultConfiguration());
        IkeChat.req(IkeChat.API_REFRESH_TOKEN, IkeChat.PARAM_RELEASE_LOCKER);
        IkeChat.req(IkeChat.API_LIST_SERVER_IPS, IkeChat.PARAM_EMPTY);
    }
}