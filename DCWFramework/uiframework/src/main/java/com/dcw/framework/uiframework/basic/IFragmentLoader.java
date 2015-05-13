package com.dcw.framework.uiframework.basic;

import com.dcw.framework.uiframework.ui.BaseFragment;

public interface IFragmentLoader {
    public static final int FRAGMENT_TYPE_NORMAL = 1;
    public static final int FRAGMENT_TYPE_DYNAMICLOAD = 2;

    public BaseFragment loadFragment(String fragmentName);
}
