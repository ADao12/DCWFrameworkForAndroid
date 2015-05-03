package com.dcw.framework.uiframework.basic;

interface IController extends IMessageHandler,EnvironmentCallback {
    public void setControllerCenterCallback(ControllerCenterCallback callback);;
    public boolean isPermanent();
    public void onInit();
}
