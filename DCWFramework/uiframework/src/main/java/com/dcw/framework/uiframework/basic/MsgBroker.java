package com.dcw.framework.uiframework.basic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * <p>Title: ucweb</p>
 *
 * <p>Description: </p>
 *  ......
 * <p>Copyright: Copyright (c) 2015</p>
 *
 * <p>Company: ucweb.com</p>
 *
 * @author JiaYing.Cheng
 * @email adao12.vip@gmail.com
 * @create 2015/3/19
 * @version 1.0
 */class MsgBroker implements Handler.Callback {
    private static final String KEY_MESSAGE_ID = "msg_id";
    private static final String KEY_MESSAGE_BODY = "msg_body";
    private static final String KEY_MESSAGE_LISTENER = "msg_listener";

    private static final int MSG_DEFAULT_ID = 1;

    private ControllerCenter mControllerCenter = null;
    private HashMap<String,String> mMessageHandlerMap = new HashMap<String, String>(2);

    private Handler mMainHandler = null;

    public  MsgBroker() {
        if (mMainHandler == null) {
            mMainHandler = new Handler(Looper.getMainLooper(),this);
        }

        checkThread();
    }

    public void checkThread() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            final Error error = new Error("create msgBroker in non-ui thread");
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    throw error;
                }
            });
        }
    }

    public void setControllerCenter(ControllerCenter center) {
        mControllerCenter = center;
    }

    public void registerMessageHandler(String messageHandlerID,String[] msgMap) {
        if (TextUtils.isEmpty(messageHandlerID)) {
            return;
        }

        if(msgMap == null || msgMap.length == 0) {
            return;
        }

        for (int i = 0; i < msgMap.length ; i++) {
            if(TextUtils.isEmpty(msgMap[i])) {
                continue;
            }

            String handlerID = mMessageHandlerMap.get(msgMap[i]);
            if(!TextUtils.isEmpty(handlerID)) {
                continue;
            }

            mMessageHandlerMap.put(msgMap[i],messageHandlerID);
        }
    }

    public void sendMessage(String messageId, Bundle messageData){
        sendMessageForResult(messageId,messageData,null);
    }

    public void sendMessageForResult(String messageId, Bundle messageData,IResultListener listener) {

        Message message = Message.obtain();
        message.what = MSG_DEFAULT_ID;

        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE_ID, messageId);
        bundle.putBundle(KEY_MESSAGE_BODY, messageData);
        bundle.putParcelable(KEY_MESSAGE_LISTENER, listener);
        message.obj = bundle;

        mMainHandler.sendMessage(message);
    }

    @Override
    public boolean handleMessage(android.os.Message msg) {
        if (msg.what != MSG_DEFAULT_ID) {
            return  false;
        }

        Bundle bundle = (Bundle)msg.obj;
        String messageID = bundle.getString(KEY_MESSAGE_ID);
        Bundle messageData = bundle.getBundle(KEY_MESSAGE_BODY);
        IResultListener listener = bundle.getParcelable(KEY_MESSAGE_LISTENER);

        String msgHandlerID = mMessageHandlerMap.get(messageID);
        if(TextUtils.isEmpty(msgHandlerID)) {
            return  false;
        }

        IMessageHandler handler = mControllerCenter.getMessageHandler(msgHandlerID);
        if (handler != null) {
            handler.handleMessage(messageID, messageData, listener);
        }
        return true;
    }
}
