package com.dcw.framework.uiframework.basic;

import android.content.Context;
import android.os.Bundle;

import com.dcw.framework.uiframework.ui.BaseFragment;

/**
 * ****************************************************************************
 * Copyright @ 2009 - 2015 www.9game.cn All Rights Reserved
 * <p/>
 * Creation    : 2015-03-18
 * Author      : lihq@ucweb.com
 * Description : Tell me what does this class do
 * ****************************************************************************
 */
class EnvironmentImpl implements Environment {

    Context mContext;
    MsgBroker mMsgBroker;
    NotificationCenter mNotificationCenter;

    @Override
    public void sendMessage(String messageId) {
        mMsgBroker.sendMessage(messageId, null);
    }

    @Override
    public void sendMessage(String messageId, Bundle messageData) {
        mMsgBroker.sendMessage(messageId,messageData);
    }

    @Override
    public void sendMessageForResult(String messageId, Bundle messageData, IResultListener listener) {
        mMsgBroker.sendMessageForResult(messageId, messageData, listener);
    }

    public void setMsgBroker(MsgBroker msgBroker) {
        mMsgBroker = msgBroker;
    }

    public void setNotificationCenter(NotificationCenter center){
        mNotificationCenter = center;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public Context getAppContext() {
        return  mContext;
    }

    @Override
    public void sendNotification(Notification notification) {
        mNotificationCenter.sendNotification(notification);
    }

    public void registerNotification(String notifycationId, INotify notify){
        mNotificationCenter.register(notifycationId, notify);
    }

    public void unregisterNotification(String notificationId, INotify notify){
        mNotificationCenter.unregister(notificationId, notify);
    }

    @Override
    public void startFragment(String fragmentName, Bundle param){
        startFragment(fragmentName,param,false,true);
    }

    @Override
    public void startFragment(String fragmentName, Bundle param ,boolean useAnim, boolean forceNew) {
        startFragmentForResult(fragmentName,param,null,useAnim,forceNew);
    }

    @Override
    public void startFragmentForResult(String fragmentName, Bundle param, IResultListener listener, boolean useAnim, boolean forceNew) {
        BaseFragment fragment = FragmentLoaderFactory.getInstance().getFragmentLoader(IFragmentLoader.FRAGMENT_TYPE_NORMAL).loadFragment(fragmentName);
        fragment.setEnvironment(this);
        fragment.setBundleArguments(param);
        fragment.setResultListener(listener);
        fragment.setUseAnim(useAnim);
        fragment.show(forceNew);
    }
}
