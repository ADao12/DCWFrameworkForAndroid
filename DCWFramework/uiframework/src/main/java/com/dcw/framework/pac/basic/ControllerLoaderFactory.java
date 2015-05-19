package com.dcw.framework.pac.basic;

/**
 * @author JiaYing.Cheng
 * @email adao12.vip@gmail.com
 * @create 2015/3/18
 * @version 1.0
 */class ControllerLoaderFactory {

    private static ControllerLoaderFactory sInstance = null;
    private  ControllerLoader mControllerNormal = null;
    private  ControllerLoaderDynamic mControllerDynamic = null;

    private  ControllerLoaderFactory() {
    }

    public static ControllerLoaderFactory getInstance() {
        if (sInstance == null) {
            sInstance = new ControllerLoaderFactory();
        }

        return sInstance;
    }

    public IControllerLoader getControlllerLoader(int controllerType) {
        IControllerLoader controllerLoader = null;
        switch (controllerType) {
            case IControllerLoader.CONTROLLER_TYPE_DYNAMICLOAD:
                if(mControllerDynamic == null) {
                    controllerLoader = mControllerDynamic = new ControllerLoaderDynamic();
                }else {
                    controllerLoader = mControllerDynamic;
                }
                break;
            default:
                if(mControllerNormal == null) {
                    controllerLoader = mControllerNormal =new ControllerLoader();
                }else {
                    controllerLoader = mControllerNormal;
                }
                break;
        }

        return controllerLoader;
    }
}
