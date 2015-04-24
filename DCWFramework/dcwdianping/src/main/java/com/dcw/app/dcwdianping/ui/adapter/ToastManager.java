package com.dcw.app.dcwdianping.ui.adapter;

import android.content.Context;
import android.widget.Toast;

/**
 * ****************************************************************************
 * Copyright @ 2009 - 2015 www.9game.cn All Rights Reserved
 * <p/>
 * Creation    : 2015-04-02
 * Author      : lihq@ucweb.com
 * Description : Tell me what does this class do
 * ****************************************************************************
 */
public class ToastManager {
    Context mContext;

    private static ToastManager gInstance = new ToastManager();

    private ToastManager(){

    }

    public static ToastManager getInstance(){
        return gInstance;
    }

    public void showToast(CharSequence text, int DurationType){
        Toast.makeText(mContext, text, DurationType).show();
    }

    public void showToast(CharSequence text){
        showToast(text, Toast.LENGTH_SHORT);
    }

    public void init(Context context){
        mContext = context;
    }
}
