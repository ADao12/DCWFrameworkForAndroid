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
public abstract class BaseController implements IController,INotify{
    Environment mEnv;
    private  ControllerCenterCallback mCenterCallback = null;

    @Override
    public void setEnvironment(Environment environment) {
        mEnv = environment;
    }

    @Override
    public Environment getEnvironment() {
        return mEnv;
    }

    @Override
    public boolean isPermanent() {
        return true;
    }

    @Override
    public void setControllerCenterCallback(ControllerCenterCallback callback) {
        mCenterCallback = callback;
    }

    @Override
    public void registerMessage(String message) {

    }

    @Override
    public void unRegisterMessage(String message) {

    }

    @Override
    public void onNotify(Notification notification) {

    }

    public void sendMessage(String messageId){
        if(mEnv != null){
            mEnv.sendMessage(messageId);
        }
    }

    public void sendMessage(String messageId, Bundle messageData){
        if(mEnv != null){
            mEnv.sendMessage(messageId, messageData);
        }
    }

    public void sendMessageForResult(String messageId, Bundle messageData,IResultListener listener){
        if(mEnv != null){
            mEnv.sendMessageForResult(messageId, messageData, listener);
        }
    }

    public void startFragment(Class fragment){
        startFragment(fragment, null);
    }

    public void startFragment(Class fragment, Bundle param){
        if(mEnv != null){
            mEnv.startFragment(fragment.getName(),param);
        }
    }

    public void startFragment(Class fragment, Bundle param, boolean useAnim, boolean forceNew){
        if(mEnv != null){
            mEnv.startFragment(fragment.getName(),param,useAnim,forceNew);
        }
    }

    public void sendNotification(String notificationID, Bundle extraData){
        if(mEnv != null){
            mEnv.sendNotification(Notification.obtain(notificationID, extraData));
        }
    }

    public void registerNotification(String notifycationId, INotify notify){
        if(mEnv != null){
            mEnv.registerNotification(notifycationId, notify);
        }
    }

    public void unregisterNotification(String notifycationId, INotify notify){
        if(mEnv != null){
            mEnv.unregisterNotification(notifycationId, notify);
        }
    }

    public void registerNotification() {
        String[] notifications = getNotification();

        for (int i = 0; i < notifications.length; i++) {
            getEnvironment().registerNotification(notifications[i], this);
        }
    }

    public void unregisterNotification(){
        String[] notifications = getNotification();

        for (int i = 0; i < notifications.length; i++) {
            getEnvironment().unregisterNotification( notifications[i], this);
        }
    }

    private String[] getNotification(){
        String[] notifications = {};
        try {
            RegisterNotifications annotation = this.getClass().getAnnotation(RegisterNotifications.class);

            if (annotation != null) {
                notifications = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notifications;
    }
}
