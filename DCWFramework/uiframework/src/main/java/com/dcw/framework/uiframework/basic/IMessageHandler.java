package com.dcw.framework.uiframework.basic;

import android.os.Bundle;

/**
 * ****************************************************************************
 * Copyright @ 2009 - 2015 www.9game.cn All Rights Reserved
 * <p/>
 * Creation    : 2015-03-18
 * Author      : lihq@ucweb.com
 * Description : Tell me what does this class do
 * ****************************************************************************
 */
interface IMessageHandler {
    public void handleMessage(String messageId, Bundle messageData, IResultListener listener);
}
