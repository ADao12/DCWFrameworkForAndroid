package com.dcw.framework.uiframework.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

class FragmentSwicher {

    private static HashMap<String, BaseFragment> gSwitchCache = new HashMap<String, BaseFragment>(2);

    private  static BaseActivity gCurrentActivity = null;

    public static void pushFragment(Context context, BaseFragment fragment, boolean isForceNew) {

        Class hostActivity = fragment.getHostActivity();

        if(gCurrentActivity != null && !gCurrentActivity.isFinishing()
                && gCurrentActivity.getClass() == hostActivity){
            //current Activity is hostActivity
            gCurrentActivity.pushFragment(fragment, isForceNew);

        }else if(hostActivity != null){
            //cache Fragment
            gSwitchCache.put(fragment.getName(), fragment);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClass(context, hostActivity);
            intent.putExtra(BaseActivity.INTENT_EXTRA_FRAGMENT_TAG, fragment.getName());
            intent.putExtra(BaseActivity.INTENT_EXTRA_IS_FORCE_NEW, isForceNew);

            context.startActivity(intent);

            //use Anim only start another activity
            if (context instanceof Activity) {
                if (fragment.isUseAnim()) {
                    ((Activity) context).overridePendingTransition(fragment.mEnterAnimRes, fragment.mExitAnimRes);

                } else {
                    ((Activity) context).overridePendingTransition(0, 0);
                }
            }

        }else if(gCurrentActivity != null){
            gCurrentActivity.pushFragment(fragment, isForceNew);
        }
    }

    public static BaseFragment popCacheFragment(String fragmentTag) {
        return gSwitchCache.remove(fragmentTag);
    }

    public static void cacheCurrentActivity(BaseActivity activity){
        gCurrentActivity = activity;
        if(gCurrentActivity.getEnvironment() != null) {
            gCurrentActivity.getEnvironment().setCurrentActivity(gCurrentActivity);
        }
    }
}
