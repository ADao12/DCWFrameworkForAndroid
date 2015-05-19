package com.dcw.framework.pac.basic;

import android.text.TextUtils;

import java.util.HashMap;

class ControllerCenter implements ControllerCenterCallback {

    private IFrameworkManifest mManifest = null;
    private Environment mEnv = null;
    private  MsgBroker           mMsgBroker = null;

    private HashMap<String,IController> mControllers = new HashMap<String, IController>();

    public  ControllerCenter() {

    }

    public void setManifest(IFrameworkManifest manifest) {
        mManifest = manifest;
    }

    public  void setEnvironment(Environment env) {
        mEnv = env;
    }

    public Environment getEnviroment(){
        return mEnv;
    }

    public void setMsgBroker(MsgBroker msgBroker) {
        mMsgBroker = msgBroker;
    }

    public void init() {
        if (mManifest == null) {
            return;
        }

        String[] controllerIDs = mManifest.getControllers();
        if (controllerIDs == null || controllerIDs.length == 0) {
            return;
        }

        for (int i = 0;i < controllerIDs.length ; i++) {
            if(TextUtils.isEmpty(controllerIDs[i])) {
                continue;
            }
            String[] messageIDs = getControllerMessages(controllerIDs[i]);
            if(messageIDs == null || messageIDs.length == 0 ) {
                continue;
            }
            mControllers.put(controllerIDs[i],null);
            mMsgBroker.registerMessageHandler(controllerIDs[i],messageIDs);
        }

        mEnv.sendMessage(FrameworkMessage.LAUNCHER_CONTROLLER_INVOKE);
    }

    public IMessageHandler getMessageHandler(String messageHandlerID) {
        IController controller = null;

        if(TextUtils.isEmpty(messageHandlerID)) {
            return  null;
        }

        controller = mControllers.get(messageHandlerID);
        if(controller == null) {
            controller = createController(messageHandlerID);
            mControllers.put(messageHandlerID,controller);
        }

        return controller;
    }

    private IController createController(String controllerID) {
        IController controller = null;

        IControllerLoader loader = ControllerLoaderFactory.getInstance().getControlllerLoader(IControllerLoader.CONTROLLER_TYPE_NORMAL);

        controller = loader.loadController(controllerID);

        if(controller != null) {
            controller.setEnvironment(mEnv);
            controller.setControllerCenterCallback(this);
            controller.onInit();
        }

        return controller;
    }

    @Override
    public void removeController(IController controller) {
        if (controller == null) {
            return;
        }

        String controllerID = controller.getClass().getName();

        if(mControllers.containsKey(controllerID)) {
            mControllers.put(controllerID,null);
        }
    }

    private String[] getControllerMessages(String controllerID) {
        if (TextUtils.isEmpty(controllerID)) {
            return null;
        }

        String[] messages = null;
        try {
            Class<?> clazz = Class.forName(controllerID);
            if(clazz == null) {
                return  null;
            }

            if(clazz.isAnnotationPresent(RegisterMessages.class)) {
                RegisterMessages registerMessages = clazz.getAnnotation(RegisterMessages.class);
                if(registerMessages != null) {
                    messages = registerMessages.value();
                }
            }
        }catch (Exception e) {
        }

        return messages;
    }
}
