package com.dcw.adaoframework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dcw.adaoframework.view.ViewFinder;
import com.dcw.adaoframework.view.annotation.ViewInject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: ucweb</p>
 * <p/>
 * <p>Description: </p>
 * ......
 * <p>Copyright: Copyright (c) 2015</p>
 * <p/>
 * <p>Company: ucweb.com</p>
 *
 * @author JiaYing.Cheng
 * @version 1.0
 * @email adao12.vip@gmail.com
 * @create 15/2/7
 */
public abstract class DActivity extends Activity {

    private static Map<Class, String> sSetListenerMethodMap = new HashMap<Class, String>();

    public void initAnnotation(final DActivity activity) {

        ViewFinder viewFinder = new ViewFinder() {
            public View findViewById(int id) { return activity.findViewById(id); }
        };
        try {
            initAnnotatedFields(DActivity.class, activity, viewFinder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initAnnotatedFields(Class clazz, Object object, ViewFinder viewFinder) throws InvocationTargetException
            , IllegalAccessException, NoSuchMethodException, InstantiationException {
        Field fields[] = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            Annotation[] annotations = field.getAnnotations();

            if (annotations == null || annotations.length == 0) {
                continue;
            }

            for (int j = 0; j < annotations.length; ++j) {
                Annotation anno = annotations[j];

                if (ViewInject.class.isAssignableFrom(anno.getClass())) {
                    ViewInject annotation = (ViewInject)anno;
                    View view = viewFinder.findViewById(annotation.value());
                    field.setAccessible(true);
                    field.set(object, view);

                    if (annotation.listeners().length > 0) {

                        setListenersForView(clazz, annotation, view, object);
                    }
                }
            }
        }
    }

    private static void setListenersForView(Class clazz, ViewInject annotation, View view, Object listener) throws InvocationTargetException
            , IllegalAccessException, NoSuchMethodException, InstantiationException {
        Class[] listeners = annotation.listeners();

        for (int j = 0; j < listeners.length; ++j) {
            Class listenerClass = listeners[j];

            if (!listenerClass.isAssignableFrom(clazz)) {
                return;
            }

            String methodName = sSetListenerMethodMap.get(listenerClass);
            if (methodName == null) {
                methodName = listenerClass.getSimpleName();

                // for interfaces from android.support.v4.**, Class.getSimpleName() may return names that contain the dollar sign
                // I have no idea whether this is a bug, the following workaround fixes the problem
                int index = methodName.lastIndexOf('$');
                if (index != -1) {
                    methodName = methodName.substring(index + 1);
                }
                methodName = "set" + methodName;

                sSetListenerMethodMap.put(listenerClass, methodName);
            }

            try {
                Method method = view.getClass().getMethod(methodName, listenerClass);
                method.invoke(view, listener);
            } catch (NoSuchMethodException e) {
                throw new NoSuchMethodException("No such method: " + listenerClass.getSimpleName() + "." + methodName
                        + ", you have to manually add the set-listener method to sSetListenerMethodMap.");
            }
        }
    }
}
