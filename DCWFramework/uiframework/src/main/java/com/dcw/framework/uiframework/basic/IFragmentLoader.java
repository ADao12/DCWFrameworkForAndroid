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
public interface IFragmentLoader {
    public static final int FRAGMENT_TYPE_NORMAL = 1;
    public static final int FRAGMENT_TYPE_DYNAMICLOAD = 2;

    public BaseFragment loadFragment(String fragmentName);
}
