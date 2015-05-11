package com.dcw.framework.uiframework.basic;

import android.app.Activity;
import android.os.Looper;

public final class FrameworkFacade {
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

    public void start(IFrameworkManifest manifest,Activity activity) {
        if (mIsStarted) {
            return;
        }

        if (manifest == null || activity == null) {
            return;
        }

        checkThread();

        controllerCenter = new ControllerCenter();
        EnvironmentImpl environment = new EnvironmentImpl();
        MsgBroker msgBroker = new MsgBroker();

        environment.setCurrentActivity(activity);
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

    public Environment getEnvironment(){
        return controllerCenter.getEnviroment();
    }

    private static void checkThread() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("Framework must be started in UI Thread");
        }
    }
}
