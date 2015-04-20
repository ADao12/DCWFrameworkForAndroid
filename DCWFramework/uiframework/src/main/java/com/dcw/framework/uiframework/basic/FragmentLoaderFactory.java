package com.dcw.framework.uiframework.basic;

/**
 * ****************************************************************************
 * Copyright @ 2009 - 2015 www.9game.cn All Rights Reserved
 * <p/>
 * Creation    : 2015-04-08
 * Author      : lihq@ucweb.com
 * Description : Tell me what does this class do
 * ****************************************************************************
 */
public class FragmentLoaderFactory {

    private static FragmentLoaderFactory gInstance;

    private FragmentLoader mFragmentLoader = null;
    private DynamicFragmentLoader mDynamicFragmentLoader = null;


    private FragmentLoaderFactory(){

    }

    public static FragmentLoaderFactory getInstance(){
        if(gInstance == null){
            synchronized (FragmentLoaderFactory.class){
                gInstance = new FragmentLoaderFactory();
            }
        }
        return gInstance;
    }

    public IFragmentLoader getFragmentLoader(int type){
        IFragmentLoader fragmentLoader = null;
        switch (type) {
            case IFragmentLoader.FRAGMENT_TYPE_DYNAMICLOAD:
                if(mDynamicFragmentLoader == null) {
                    fragmentLoader = mDynamicFragmentLoader = new DynamicFragmentLoader();
                }else {
                    fragmentLoader = mDynamicFragmentLoader;
                }
                break;
            default:
                if(mFragmentLoader == null) {
                    fragmentLoader = mFragmentLoader = new FragmentLoader();
                }else {
                    fragmentLoader = mFragmentLoader;
                }
                break;
        }

        return fragmentLoader;
    }
}