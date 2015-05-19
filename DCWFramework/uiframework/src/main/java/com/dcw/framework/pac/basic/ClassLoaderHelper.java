package com.dcw.framework.pac.basic;

import android.text.TextUtils;

import java.lang.reflect.Constructor;

public class ClassLoaderHelper {

    //动态加载设计时再考虑
//    private static Class<?> getClass(Context context,String classFullName) {
//        Class<?> clazz = null;
//        // 1: try Class.forName
//        try {
//            clazz = Class.forName(classFullName);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // 2: try DexLoader if prev step fail
//        if (clazz == null) {
//            try {
//                clazz = context.getClassLoader().loadClass(classFullName);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return clazz;
//    }

    public static Object loadClass(String classFullName) {
        if (TextUtils.isEmpty(classFullName)) {
            return null;
        }

        Object object = null;
        try {
            Class<?> clazz = Class.forName(classFullName);
            if(clazz == null) {
                return null;
            }

            Constructor<?> constructor = clazz.getDeclaredConstructor();
            object = constructor.newInstance();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return  object;
    }
}
