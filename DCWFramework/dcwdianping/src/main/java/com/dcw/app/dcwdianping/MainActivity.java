package com.dcw.app.dcwdianping;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dcw.app.dcwdianping.ui.adapter.BaseActivityWrapper;
import com.dcw.app.dcwdianping.ui.adapter.FrameworkManifest;
import com.dcw.app.dcwdianping.ui.adapter.ToastManager;
import com.dcw.framework.uiframework.basic.FrameworkFacade;
import com.dcw.framework.uiframework.ui.BaseFragment;


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
