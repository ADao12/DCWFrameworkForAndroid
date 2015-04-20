package com.dcw.framework.uiframework.basic;

/**
 * <p>Title: ucweb</p>
 *
 * <p>Description: </p>
 *  ......
 * <p>Copyright: Copyright (c) 2015</p>
 *
 * <p>Company: ucweb.com</p>
 *
 * @author JiaYing.Cheng
 * @email adao12.vip@gmail.com
 * @create 2015/3/18
 * @version 1.0
 */interface IControllerLoader {
    public static final int CONTROLLER_TYPE_NORMAL = 1;
    public static final int  CONTROLLER_TYPE_DYNAMICLOAD = 2;

    public IController loadController(String controllerID);
}
