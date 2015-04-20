package com.dcw.framework.uiframework.basic;

/**
 * ****************************************************************************
 * Copyright @ 2009 - 2015 www.9game.cn All Rights Reserved
 * <p/>
 * Creation    : 2015-03-30
 * Author      : lihq@ucweb.com
 * Description : 尽量在业务controller实现该接口，UI只有才主题改变这种才会实现该接口
 * ****************************************************************************
 */
public interface INotify {
    public void onNotify(Notification notification);
}
