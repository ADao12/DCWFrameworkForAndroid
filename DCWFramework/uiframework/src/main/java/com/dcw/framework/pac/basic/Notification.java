package com.dcw.framework.pac.basic;

import android.os.Bundle;

public class Notification {
    public String mId;
    public Bundle mBundleData;

    public Notification(String id, Bundle bundle){
        mId= id;
        mBundleData = bundle;
    }

    public Notification(String id){
        this(id, null);
    }

    public static Notification obtain(String id, Bundle bundle){
        return new Notification(id,bundle);
    }

    public static Notification obtain(String id){
        return new Notification(id);
    }
}
