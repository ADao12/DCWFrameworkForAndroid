package com.dcw.app.rating;

import android.os.Bundle;
import android.util.Log;

import com.dcw.framework.uiframework.basic.BaseController;
import com.dcw.framework.uiframework.basic.FrameworkMessage;
import com.dcw.framework.uiframework.basic.IResultListener;
import com.dcw.framework.uiframework.basic.Notification;
import com.dcw.framework.uiframework.basic.RegisterMessages;
import com.dcw.framework.uiframework.basic.RegisterNotifications;

@RegisterMessages({MessageDef.REQUEST, FrameworkMessage.LAUNCHER_CONTROLLER_INVOKE})
@RegisterNotifications({NotificationDef.CHANGE_USER_NAME})
public class ModuleController extends BaseController {

    @Override
    public void handleMessage(String messageId, Bundle messageData, IResultListener listener) {
        if (messageId == MessageDef.REQUEST) {
            Bundle bundle = new Bundle();
            bundle.putString("CODE", "200");
            listener.onResult(bundle);
        } else if (messageId == FrameworkMessage.LAUNCHER_CONTROLLER_INVOKE) {
            startFragment(RichTextFragment.class);
        }
    }

    @Override
    public void onNotify(Notification notification) {
        if (NotificationDef.CHANGE_USER_NAME.equals(notification.mId)) {
            Bundle bundleData = notification.mBundleData;
            String name = bundleData.getString(BundleConstant.NOTIFICATION_NAME);
            Log.d("notify", getClass().getName() + ":" + name);
        }
    }
}
