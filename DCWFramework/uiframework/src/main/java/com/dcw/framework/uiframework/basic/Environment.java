package com.dcw.framework.uiframework.basic;


import android.app.Activity;
import android.os.Bundle;

public interface Environment {
    public void sendMessage(String messageId);
    public void sendMessage(String messageId, Bundle messageData);
    public void sendMessageForResult(String messageId, Bundle messageData, IResultListener listener);
    public Bundle sendMessageSync(String messageId);
    public Bundle sendMessageSync(String messageId, Bundle messageData);
    public Activity getCurrentActivity();
    public void setCurrentActivity(Activity activity);
    public void sendNotification(Notification notification);
    public void registerNotification(String notifycationId, INotify notify);
    public void unregisterNotification(String notificationId, INotify notify);
    public void startFragment(String fragmentName, Bundle param);
    public void startFragment(String fragmentName, Bundle param, boolean useAnim, boolean forceNew);
    public void startFragmentForResult(String fragmentName, Bundle param, IResultListener listener, boolean useAnim, boolean forceNew);
}
