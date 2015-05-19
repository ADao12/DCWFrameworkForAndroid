package com.dcw.framework.pac.ui;


import android.app.Activity;
import android.content.Intent;

import java.util.HashMap;

class FragmentSwicher {

    private static HashMap<String, BaseFragment> gSwitchCache = new HashMap<String, BaseFragment>(2);

    public static void pushFragment(Activity baseActivity, BaseFragment fragment, boolean isForceNew) {

            Class hostActivity = fragment.getHostActivity();

            if (baseActivity != null && !baseActivity.isFinishing()
                    && baseActivity.getClass() == hostActivity
                    && baseActivity instanceof BaseActivity
                    && ((BaseActivity)baseActivity).isForeground()) {
                //current Activity is hostActivity
                ((BaseActivity)baseActivity).pushFragment(fragment, isForceNew);

            } else if (hostActivity != null) {
                //cache Fragment
                gSwitchCache.put(fragment.getName(), fragment);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(baseActivity, hostActivity);
                intent.putExtra(BaseActivity.INTENT_EXTRA_FRAGMENT_TAG, fragment.getName());
                intent.putExtra(BaseActivity.INTENT_EXTRA_IS_FORCE_NEW, isForceNew);

                baseActivity.startActivity(intent);

                //use Anim only start another activity
                if (fragment.isUseAnim()) {
                    baseActivity.overridePendingTransition(fragment.mEnterAnimRes, fragment.mExitAnimRes);
                } else {
                    baseActivity.overridePendingTransition(0, 0);
                }

            } else if (baseActivity != null && baseActivity instanceof BaseActivity) {
                ((BaseActivity)baseActivity).pushFragment(fragment, isForceNew);
            }
    }

    public static BaseFragment popCacheFragment(String fragmentTag) {
        return gSwitchCache.remove(fragmentTag);
    }
}
