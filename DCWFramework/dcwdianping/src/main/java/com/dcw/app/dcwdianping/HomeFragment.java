package com.dcw.app.dcwdianping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcw.app.dcwdianping.ui.adapter.BaseFragmentWrapper;

public class HomeFragment extends BaseFragmentWrapper {

    @Override
    public Class getHostActivity() {
        return MainActivity.class;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home,null);
//        rootView.findViewById(R.id.home).setOnClickListener(this);
//        rootView.findViewById(R.id.discover).setOnClickListener(this);
//        rootView.findViewById(R.id.mine).setOnClickListener(this);

//        return rootView;
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDefault();
    }

    @Override
    public void onClick(View view) {
    }

    public void initDefault(){
//        startFragment(PageOne.class);
    }
}
