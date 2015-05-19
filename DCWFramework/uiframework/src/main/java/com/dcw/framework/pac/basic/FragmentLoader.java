package com.dcw.framework.pac.basic;

import com.dcw.framework.pac.ui.BaseFragment;

public class FragmentLoader implements IFragmentLoader {
    @Override
    public BaseFragment loadFragment(String fragmentName) {
        BaseFragment fragment = (BaseFragment)ClassLoaderHelper.loadClass(fragmentName);
        return fragment;
    }
}
