package com.dcw.framework.pac.basic;

/**
 * @author JiaYing.Cheng
 * @email adao12.vip@gmail.com
 * @create 2015/3/18
 * @version 1.0
 */interface IControllerLoader {
    public static final int CONTROLLER_TYPE_NORMAL = 1;
    public static final int  CONTROLLER_TYPE_DYNAMICLOAD = 2;

    public IController loadController(String controllerID);
}
