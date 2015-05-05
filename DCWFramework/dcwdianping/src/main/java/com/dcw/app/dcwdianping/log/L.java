package com.dcw.app.dcwdianping.log;

import android.util.Log;
import android.webkit.ConsoleMessage.MessageLevel;

public class L {
    // webview打log的各种等级
    private static final int WEB_LEVEL_DEBUG = MessageLevel.DEBUG.ordinal();
    private static final int WEB_LEVEL_LOG = MessageLevel.LOG.ordinal();
    private static final int WEB_LEVEL_TIP = MessageLevel.TIP.ordinal();
    private static final int WEB_LEVEL_WARNING = MessageLevel.WARNING.ordinal();
    private static final int WEB_LEVEL_ERROR = MessageLevel.ERROR.ordinal();
    
    public static void d(String message, Object...args) {
    }

    public static void i(String message, Object...args) {

    }

    public static void w(String message, Object...args) {
    }
    
    public static void w(Throwable e) {
    }

    public static void e(String message, Object...args) {
    }
    
    public static void e(Throwable e) {
    }

    public static void v(String message, Object...args) {
    }
    
    public static void t(String message, Object...args) {
        v(message, args);
    }
    
    public static void f(String message, Object...args) {
    }
    
    public static void s(String message, Object...args) {
    }
    
    private static String formatMessage(String message, Object...args) {
        if (message == null) {
            return "";
        }
        if (args != null && args.length > 0) {
            try {
                return String.format(message, args);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return message;
    }
    
    /**
     * 获取native日志tag
     * @return
     */
    private static String getTag() {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];

        String className = stackTrace.getClassName();
        String tag = className.substring(className.lastIndexOf('.') + 1)
                + "." + stackTrace.getMethodName() + "#" + stackTrace.getLineNumber();
        return tag;
    }

    /**
     * 打印到std
     * @param level
     * @param tag
     * @param message
     */
    private static void print(int level, String tag, String message) {
        if (message == null)
        {
            message = message + "";
        }
        switch (level) {
        case Log.VERBOSE:
            Log.v(tag, message);
            break;

        case Log.DEBUG:
            Log.d(tag, message);
            break;

        case Log.INFO:
            Log.i(tag, message);
            break;

        case Log.WARN:
            Log.w(tag, message);
            break;

        case Log.ERROR:
            Log.e(tag, message);
            break;

        }
    }
}
