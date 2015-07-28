package com.dcw.app.rating.biz.home;

import android.os.Bundle;
import android.view.View;

import com.dcw.app.rating.biz.MainActivity;
import com.dcw.app.rating.ui.adapter.BaseFragmentWrapper;

public class HomeFragment extends BaseFragmentWrapper {

    @Override
    public Class getHostActivity() {
        return MainActivity.class;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDefault();
    }

    @Override
    public void onClick(View view) {
    }

    public void initDefault() {
//        startFragment(PageOne.class);
    }
}
