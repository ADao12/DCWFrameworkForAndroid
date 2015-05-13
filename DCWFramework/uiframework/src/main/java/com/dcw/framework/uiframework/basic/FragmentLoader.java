package com.dcw.framework.uiframework.basic;

import com.dcw.framework.uiframework.ui.BaseFragment;

public class FragmentLoader implements IFragmentLoader {
    @Override
    public BaseFragment loadFragment(String fragmentName) {
        BaseFragment fragment = (BaseFragment)ClassLoaderHelper.loadClass(fragmentName);
        return fragment;
    }
}
