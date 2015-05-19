package com.dcw.framework.pac.basic;

interface IController extends IMessageHandler,EnvironmentCallback {
    public void setControllerCenterCallback(ControllerCenterCallback callback);;
    public boolean isPermanent();
    public void onInit();
}
