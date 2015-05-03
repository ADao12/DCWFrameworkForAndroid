package com.dcw.framework.uiframework.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.dcw.framework.uiframework.basic.Environment;
import com.dcw.framework.uiframework.basic.EnvironmentCallback;
import com.dcw.framework.uiframework.basic.INotify;
import com.dcw.framework.uiframework.basic.IResultListener;
import com.dcw.framework.uiframework.basic.Notification;
import com.dcw.framework.uiframework.basic.RegisterNotifications;

public abstract class BaseFragment extends Fragment implements View.OnClickListener,EnvironmentCallback,INotify {

    private IResultListener mResultListener;

    private static final long ITEM_CLICK_COOLING_TIME_IN_MS = 500;
    private long mLastItemClickTimeInMS;
    private Bundle mBundle = new Bundle();

    private Environment mEnv;

    private boolean mUseAnim;

    public int mEnterAnimRes;
    public int mExitAnimRes;
    public int mPopEnterAnimRes;
    public int mPopExitAnimRes;

    public abstract Class getHostActivity();

    public int getContainer(){
        return android.R.id.content;
    }

    public void setUseAnim(boolean useAnim){
        mUseAnim = useAnim;
    }

    public boolean isUseAnim(){
        return mUseAnim;
    }

    public void setCustomAnimations(int enterAnimRes, int exitAnimRes, int popEnterAnimRes, int popExitAnimRes){
        mEnterAnimRes = enterAnimRes;
        mExitAnimRes = exitAnimRes;
        mPopEnterAnimRes = popEnterAnimRes;
        mPopExitAnimRes = popExitAnimRes;
    }

    @Deprecated
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    public void setBundleArguments(Bundle bundle){
        mBundle = bundle;
    }

    public Bundle getBundleArguments(){
        return mBundle;
    }

    public String getName(){
        return this.getClass().getName();
    }

    public void onResult(Bundle result){
        if(mResultListener != null){
            mResultListener.onResult(result);
        }
    }

    public void setResultListener(IResultListener listener) {
        this.mResultListener = listener;
    }

    @Override
    public void setEnvironment(Environment environment) {
        mEnv = environment;
    }

    @Override
    public Environment getEnvironment() {
        return mEnv;
    }

    public void sendMessage(String messageId){
        if(mEnv != null){
            mEnv.sendMessage(messageId);
        }
    }

    public void sendMessage(String messageId, Bundle messageData){
        if(mEnv != null){
            mEnv.sendMessage(messageId, messageData);
        }
    }

    public void sendMessageForResult(String messageId, Bundle messageData,IResultListener listener){
        if(mEnv != null){
            mEnv.sendMessageForResult(messageId, messageData, listener);
        }
    }

    public void startFragment(Class fragment){
        startFragment(fragment, null);
    }

    public void startFragment(Class fragment, Bundle param){
        if(mEnv != null){
            mEnv.startFragment(fragment.getName(),param);
        }
    }

    public void startFragment(Class fragment, Bundle param, boolean useAnim, boolean forceNew){
        if(mEnv != null){
            mEnv.startFragment(fragment.getName(),param,useAnim,forceNew);
        }
    }

    public void startFragmentForResult(Class fragment, Bundle param, IResultListener listener){
        if(mEnv != null){
            mEnv.startFragmentForResult(fragment.getName(),param,listener,false,false);
        }
    }

    public void sendNotification(String notificationID, Bundle extraData){
        if(mEnv != null){
            mEnv.sendNotification(Notification.obtain(notificationID, extraData));
        }
    }

    public void registerNotification(String notifycationId, INotify notify){
        if(mEnv != null){
            mEnv.registerNotification(notifycationId, notify);
        }
    }

    public void unregisterNotification(String notifycationId, INotify notify){
        if(mEnv != null){
            mEnv.unregisterNotification(notifycationId, notify);
        }
    }

    private void registerNotification() {
        String[] notifications = getNotification();

        for (int i = 0; i < notifications.length; i++) {
            getEnvironment().registerNotification(notifications[i], this);
        }
    }

    private void unregisterNotification(){
        String[] notifications = getNotification();

        for (int i = 0; i < notifications.length; i++) {
            getEnvironment().unregisterNotification( notifications[i], this);
        }
    }

    private String[] getNotification(){
        String[] notifications = {};
        try {
            RegisterNotifications annotation = this.getClass().getAnnotation(RegisterNotifications.class);

            if (annotation != null) {
                notifications = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notifications;
    }

    public void show(boolean forceNew){
        FragmentSwicher.pushFragment(mEnv.getAppContext(), this, forceNew);
    }


    public void onBackPressed() {
        if (getActivity() != null) {
            if (getActivity().getCurrentFocus() != null) {
                IBinder binder = getActivity().getCurrentFocus().getWindowToken();
                if( binder != null){
                    hideKeyboard(getActivity(),binder);
                }
            }
            getActivity().onBackPressed();
        }
    }

    public void scrollToTop() {

    }

    public static boolean hideKeyboard(Context ctx, IBinder binder) {
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm.isActive()) {
            return imm.hideSoftInputFromWindow(binder, 0);
        }

        return false;
    }

    /**
     * 处理返回事件
     * @return true表示在fragment内部已经完成了对返回事件的处理，外部不需要再处理;
     * false表示外部需要对返回事件继续处理
     * */
    public boolean goBack() {
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerNotification();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNotification();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        //防止用户点击速度过快而引起多个窗口or对话框弹出.//FIXME lihq
        if (overCoolingTime()){
            this.mLastItemClickTimeInMS  = System.currentTimeMillis();
        }else{
            return;
        }
    }

    private boolean overCoolingTime() {
        return (System.currentTimeMillis() - this.mLastItemClickTimeInMS) >= ITEM_CLICK_COOLING_TIME_IN_MS;
    }

    @Override
    public void onNotify(Notification notification) {

    }
}
