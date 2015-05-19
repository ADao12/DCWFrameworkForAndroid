package com.dcw.framework.pac.basic;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

class NotificationCenter {
    private HashMap<String,ArrayList<WeakReference<INotify>>> mNotifyMap = new HashMap<String, ArrayList<WeakReference<INotify>>>();

    private static final int NOTIFICATION_MESSAGE_ID = 2;

    public Handler mHandler;

    public NotificationCenter() {

    }

    public void init(){
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == NOTIFICATION_MESSAGE_ID) {
                    notifyInner((Notification) message.obj);
                    return true;
                }
                return false;
            }
        });
    }

    public void register(String notificationID, INotify notify){
        if(mNotifyMap.get(notificationID) == null){
            mNotifyMap.put(notificationID, new ArrayList<WeakReference<INotify>>());
        }
        mNotifyMap.get(notificationID).add(new WeakReference<INotify>(notify));
    }

    public void unregister(String notificationID, INotify notify){

        ArrayList<WeakReference<INotify>> refList = mNotifyMap.get(notificationID);

        int size = refList.size();
        for (int i=0; i< size; i++){
            WeakReference<INotify> ref = refList.get(i);
            if(ref == null){
                continue;
            }

            INotify refNotify = ref.get();
            if(refNotify != null && refNotify == notify){
                refList.remove(ref);
                break;
            }
        }
    }

    public void sendNotification(Notification notification){
        if(isMainThread()){
            notifyInner(notification);
        }else{
            mHandler.sendMessage(mHandler.obtainMessage(NOTIFICATION_MESSAGE_ID, notification));
        }
    }

    public void sendNotification(Notification notification,long delay){
        mHandler.sendMessageDelayed(mHandler.obtainMessage(NOTIFICATION_MESSAGE_ID, notification), delay);
    }

    private void notifyInner(Notification notification){
        ArrayList<WeakReference<INotify>> weakRefList = mNotifyMap.get(notification.mId);

        if (weakRefList != null ){
            int size = weakRefList.size();
            for (int i=0; i < size; i ++){
                WeakReference<INotify> weakNotify = weakRefList.get(i);
                if(weakNotify != null){
                    INotify notify = weakNotify.get();
                    if(notify != null){
                        notify.onNotify(notification);
                    }
                }
            }
        }
        //考虑注册清理
    }

    public static boolean isMainThread(){
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
