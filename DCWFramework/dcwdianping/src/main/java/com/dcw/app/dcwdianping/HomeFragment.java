package com.dcw.app.dcwdianping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ****************************************************************************
 * Copyright @ 2009 - 2015 www.9game.cn All Rights Reserved
 * <p/>
 * Creation    : 2015-03-25
 * Author      : lihq@ucweb.com
 * Description : Tell me what does this class do
 * ****************************************************************************
 */
public class HomeFragment extends MainFragment {
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
//        switch (view.getId() ){
//            case R.id.home: {
//                startFragment(PageOne.class);
//                break;
//            }
//            case R.id.discover: {
//                startFragment(PageTwo.class);
//                break;
//            }
//            case R.id.mine: {
//                startFragment(PageThree.class);
//                break;
//            }
//        }
    }

    public void initDefault(){
//        startFragment(PageOne.class);
    }
}
