package com.dcw.framework.uiframework.basic;

import android.os.Bundle;

/**
 * ****************************************************************************
 * Copyright @ 2009 - 2015 www.9game.cn All Rights Reserved
 * <p/>
 * Creation    : 2015-03-30
 * Author      : lihq@ucweb.com
 * Description : Tell me what does this class do
 * ****************************************************************************
 */
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
