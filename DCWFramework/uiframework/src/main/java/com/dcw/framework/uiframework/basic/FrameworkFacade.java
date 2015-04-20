package com.dcw.framework.uiframework.basic;

import android.content.Context;
import android.os.Looper;

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
 */public final class FrameworkFacade {
    private  static FrameworkFacade sInstance = null;
    private boolean mIsStarted = false;

    ControllerCenter controllerCenter;

    private  FrameworkFacade() {
    }

    public static FrameworkFacade getInstance() {
        if (sInstance == null) {
            sInstance = new FrameworkFacade();
        }

        return  sInstance;
    }

    public void start(IFrameworkManifest manifest,Context context) {
        if (mIsStarted) {
            return;
        }

        if (manifest == null || context == null) {
            return;
        }

        checkThread();

        controllerCenter = new ControllerCenter();
        EnvironmentImpl environment = new EnvironmentImpl();
        MsgBroker msgBroker = new MsgBroker();

        environment.setContext(context);
        environment.setMsgBroker(msgBroker);

        msgBroker.setControllerCenter(controllerCenter);

        controllerCenter.setEnvironment(environment);
        controllerCenter.setManifest(manifest);
        controllerCenter.setMsgBroker(msgBroker);
        controllerCenter.init();

        NotificationCenter notificationCenter = new NotificationCenter();
        notificationCenter.init();
        environment.setNotificationCenter(notificationCenter);

    }

    private static void checkThread() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("Framework must be started in UI Thread");
        }
    }
}
