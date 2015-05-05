package com.dcw.app.rating.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dcw.framework.uiframework.ui.BaseActivity;
import com.dcw.framework.uiframework.ui.BaseFragment;

public class BaseActivityWrapper extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void pushFragment(BaseFragment fragment, boolean isForceNew) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fT = fragmentManager.beginTransaction();

        if (fragment.isUseAnim()) {
            fT.setCustomAnimations(fragment.mEnterAnimRes, fragment.mExitAnimRes,
                    fragment.mPopEnterAnimRes, fragment.mPopExitAnimRes);
        }

        fT.replace(fragment.getContainer(), fragment, fragment.getClass().getName());
        fT.commit();
    }
}
