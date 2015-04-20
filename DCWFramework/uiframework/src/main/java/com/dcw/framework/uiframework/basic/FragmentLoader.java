package com.dcw.framework.uiframework.basic;

import com.dcw.framework.uiframework.ui.BaseFragment;

/**
 * ****************************************************************************
 * Copyright @ 2009 - 2015 www.9game.cn All Rights Reserved
 * <p/>
 * Creation    : 2015-04-08
 * Author      : lihq@ucweb.com
 * Description : Tell me what does this class do
 * ****************************************************************************
 */
public class FragmentLoader implements IFragmentLoader {
    @Override
    public BaseFragment loadFragment(String fragmentName) {
        BaseFragment fragment = (BaseFragment)ClassLoaderHelper.loadClass(fragmentName);
        return fragment;
    }
}
