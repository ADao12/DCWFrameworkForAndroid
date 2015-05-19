package com.dcw.framework.pac.basic;

import android.text.TextUtils;

import java.lang.reflect.Constructor;

/**
 * @author JiaYing.Cheng
 * @email adao12.vip@gmail.com
 * @create 2015/3/19
 * @version 1.0
 */class ControllerLoader implements IControllerLoader {
        private static final String TAG = "ControllerLoader";

        public  ControllerLoader () {

        }

        @Override
        public IController loadController(String controllerID) {
            if (TextUtils.isEmpty(controllerID)) {
                return null;
            }

            IController controller = null;
            try {
                Class<?> clazz = Class.forName(controllerID);
                if(clazz == null) {
                    return null;
                }

                Constructor<?> constructor = clazz.getDeclaredConstructor();
                Object obj = constructor.newInstance();
                if(obj instanceof IController) {
                    controller = (IController) obj;
                }

            } catch(Exception e) {
                e.printStackTrace();
            }

            return  controller;
        }
}
