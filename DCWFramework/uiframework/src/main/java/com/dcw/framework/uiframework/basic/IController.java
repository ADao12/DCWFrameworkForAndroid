package com.dcw.framework.uiframework.basic;

/**
 * ****************************************************************************
 * Copyright @ 2009 - 2015 www.9game.cn All Rights Reserved
 * <p/>
 * Creation    : 2015-03-18
 * Author      : lihq@ucweb.com
 * Description : Tell me what does this class do
 * ****************************************************************************
 */
interface IController extends IMessageHandler,EnvironmentCallback {
    public void setControllerCenterCallback(ControllerCenterCallback callback);
    public void registerMessage(String message);
    public void unRegisterMessage(String message);
    public boolean isPermanent();
}
