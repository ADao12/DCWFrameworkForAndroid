package com.dcw.framework.pac.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.dcw.framework.pac.basic.Environment;

public class BaseActivity extends FragmentActivity {

    public final static String INTENT_EXTRA_FRAGMENT_TAG = "ftag";
    public final static String INTENT_EXTRA_IS_FORCE_NEW = "isForceNew";
    private Environment mEnv = null;
    private boolean mIsForeground = false;

    public void setEnvironment(Environment env) {
        mEnv = env;
    }

    public Environment getEnvironment() {
        return  mEnv;
    }

    public boolean isForeground(){
        return mIsForeground;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }

    public void handleIntent(Intent intent){
        if (intent == null) {
            return;
        }
        setIntent(intent);

        String fragmentTag = intent.getStringExtra(INTENT_EXTRA_FRAGMENT_TAG);
        boolean isForceNew = intent.getBooleanExtra(INTENT_EXTRA_IS_FORCE_NEW,true);

        if(!TextUtils.isEmpty(fragmentTag)){
            BaseFragment fragment = FragmentSwicher.popCacheFragment(fragmentTag);
            pushFragment(fragment, isForceNew);
            if(fragment != null && fragment.getEnvironment() != null) {
                setEnvironment(fragment.getEnvironment());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIsForeground = true;
        if(mEnv != null){
            mEnv.setCurrentActivity(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mIsForeground = false;
    }

    protected void pushFragment(BaseFragment fragment, boolean isForceNew) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fT = fragmentManager.beginTransaction();

        if (fragment.isUseAnim()) {
            fT.setCustomAnimations(fragment.mEnterAnimRes, fragment.mExitAnimRes,
                    fragment.mPopEnterAnimRes, fragment.mPopExitAnimRes);
        }

        if (!isForceNew) {
            BaseFragment fragmentInBackstack = (BaseFragment)fragmentManager.findFragmentByTag(fragment.getClass().getName());
            if(fragmentInBackstack != null){
                fragment = fragmentInBackstack;
            }
        }

        //防止重复添加同一个fragment
        if (fragment == fragmentManager.findFragmentById(fragment.getContainer())) {
            return;
        }

        if (fragment != null) {
            fT.replace(android.R.id.content, fragment, fragment.getClass().getName());
            fT.addToBackStack(fragment.getClass().getName());
        }

        fT.commit();
//        fragmentManager.executePendingTransactions();//可以让UI线程马上执行这个commit
    }

    @Override
    public void onBackPressed() {
        Fragment baseFragment = getCurrentFragment();
        if(baseFragment instanceof  BaseFragment){
            if(((BaseFragment)baseFragment).goBack()){
                return;
            }
        }
        /* 解决fragment addToBackStack后，按返回键出现空白的Activity问题 */
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();

        } else {
            super.onBackPressed();
        }
    }

    public Fragment getCurrentFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        return fragment;
    }

}
