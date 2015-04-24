package com.dcw.app.dcwdianping.ui.adapter;

import android.os.Bundle;

import com.dcw.framework.uiframework.ui.BaseFragment;

public abstract class BaseFragmentWrapper extends BaseFragment {

    public BaseFragmentWrapper(){
        super();
        setUseAnim(false);
//        setCustomAnimations(R.anim.open_slide_in, R.anim.open_slide_out,R.anim.close_slide_in, R.anim.close_slide_out);
    }

    @Override
    public abstract Class getHostActivity();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
