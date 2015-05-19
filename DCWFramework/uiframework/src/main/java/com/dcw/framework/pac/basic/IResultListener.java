package com.dcw.framework.pac.basic;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * ****************************************************************************
 * Copyright @ 2009 - 2015 www.9game.cn All Rights Reserved
 * <p/>
 * Creation    : 2015-03-18
 * Author      : lihq@ucweb.com
 * Description : Tell me what does this class do
 * ****************************************************************************
 */
public abstract class IResultListener implements Parcelable {
    public abstract void onResult(Bundle result);
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
