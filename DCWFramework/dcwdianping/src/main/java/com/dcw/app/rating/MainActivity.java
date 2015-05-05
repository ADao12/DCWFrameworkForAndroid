package com.dcw.app.rating;

import android.os.Bundle;

import com.dcw.app.rating.ui.adapter.BaseActivityWrapper;
import com.dcw.app.rating.ui.adapter.FrameworkManifest;
import com.dcw.app.rating.ui.adapter.ToastManager;
import com.dcw.framework.uiframework.basic.FrameworkFacade;


public class MainActivity extends BaseActivityWrapper {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFramework();
    }

    public void startFramework() {
        FrameworkFacade.getInstance().start(new FrameworkManifest(),this);
        ToastManager.getInstance().init(this);
    }
}
