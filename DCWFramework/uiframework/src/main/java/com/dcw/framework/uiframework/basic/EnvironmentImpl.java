package com.dcw.framework.uiframework.basic;

import android.content.Context;
import android.os.Bundle;

import com.dcw.framework.uiframework.ui.BaseFragment;

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

    @Override
    public Bundle sendMessageSync(String messageId) {
        return mMsgBroker.sendMessageSync(messageId);
    }

    @Override
    public Bundle sendMessageSync(String messageId, Bundle messageData) {
        return mMsgBroker.sendMessageSync(messageId,messageData);
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
        if(param != null) {
            fragment.setBundleArguments(param);
        }
        fragment.setResultListener(listener);
        fragment.setUseAnim(useAnim);
        fragment.show(forceNew);
    }
}
