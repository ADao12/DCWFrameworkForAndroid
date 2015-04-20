package com.dcw.framework.uiframework.basic;


import android.content.Context;
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
public interface Environment {
    public void sendMessage(String messageId);
    public void sendMessage(String messageId, Bundle messageData);
    public void sendMessageForResult(String messageId, Bundle messageData, IResultListener listener);
    public Context getAppContext();
    public void sendNotification(Notification notification);
    public void registerNotification(String notifycationId, INotify notify);
    public void unregisterNotification(String notificationId, INotify notify);
    public void startFragment(String fragmentName, Bundle param);
    public void startFragment(String fragmentName, Bundle param, boolean useAnim, boolean forceNew);
    public void startFragmentForResult(String fragmentName, Bundle param, IResultListener listener, boolean useAnim, boolean forceNew);
}
