package com.dcw.framework.uiframework.basic;

import android.os.Bundle;

interface IMessageHandler {
    public void handleMessage(String messageId, Bundle messageData, IResultListener listener);
    public Bundle handleMessageSync(String messageId, Bundle messageData);
}
