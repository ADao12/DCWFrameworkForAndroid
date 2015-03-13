package com.dcw.framework.dcwframework;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.dcw.adaoframework.view.DCWAnnotation;
import com.dcw.adaoframework.view.annotation.InjectLayout;
import com.dcw.adaoframework.view.annotation.InjectView;
import com.devspark.appmsg.AppMsg;

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
 * @create 15/3/12
 */
@InjectLayout(R.layout.fragment_app_msg)
public class AppMsgFragment extends Fragment implements View.OnClickListener{

    private static final int NORMAL_POSITION = 1;
    private static final int INFO_POSITION = 2;

    private int mMsgCount;
    @InjectView(R.id.style_spnr)
    private Spinner mStyle;
    @InjectView(R.id.priority_spnr)
    private Spinner mPriority;
    @InjectView(R.id.provided_txt)
    private EditText mProvidedMsg;
    @InjectView(R.id.bottom)
    private CheckBox mBottom;
    @InjectView(R.id.parent_chk)
    private CheckBox mParent;
    @InjectView(R.id.alt_parent)
    private ViewGroup mAltParent;
    @InjectView(R.id.animated_root)
    private ViewGroup animatedRoot;
    @InjectView(value = R.id.show, listeners = {View.OnClickListener.class})
    private Button mBtnShow;
    @InjectView(value = R.id.cancel_all, listeners = {View.OnClickListener.class})
    private Button mBtnCancelAll;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View convertView = inflater.inflate(R.layout.fragment_app_msg, null);
        DCWAnnotation.inject(this, convertView);
        mStyle.setSelection(INFO_POSITION);
        mPriority.setSelection(NORMAL_POSITION);

        mParent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAltParent.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                mBottom.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            }
        });

//        if (SDK_INT >= JELLY_BEAN) {
        enableChangingTransition();
//        }

        return convertView;

    }


    @TargetApi(14)
    private void enableChangingTransition() {
//        ViewGroup animatedRoot = (ViewGroup) getActivity().findViewById(R.id.animated_root);
        animatedRoot.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
    }

    /**
     * Button onClick listener.
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show:
                showAppMsg();
                break;
            case R.id.cancel_all:
                AppMsg.cancelAll(getActivity());
                break;
            default:
                return;
        }
    }

    private void showAppMsg() {
        mMsgCount++;
        final int styleSelected = mStyle.getSelectedItemPosition();
        final int priority = positionToPriority(mPriority.getSelectedItemPosition());
        final CharSequence providedMsg = mProvidedMsg.getText();
        final CharSequence msg = TextUtils.isEmpty(providedMsg)
                ? new StringBuilder().append(mStyle.getSelectedItem())
                .append(" ").append(mPriority.getSelectedItem())
                .append(" msg#").append(mMsgCount).toString()
                : providedMsg;
        final AppMsg.Style style;
        boolean customAnimations = false;
        AppMsg provided = null;
        switch (styleSelected) {
            case 0:
                style = AppMsg.STYLE_ALERT;
                break;
            case 1:
                style = AppMsg.STYLE_CONFIRM;
                break;
            case 3:
                style = new AppMsg.Style(AppMsg.LENGTH_SHORT, R.color.custom);
                customAnimations = true;
                break;
            case 4:
                style = new AppMsg.Style(AppMsg.LENGTH_STICKY, R.color.sticky);
                provided = AppMsg.makeText(getActivity(), msg, style, R.layout.sticky);
                provided.getView()
                        .findViewById(R.id.remove_btn)
                        .setOnClickListener(new CancelAppMsg(provided));
                break;
            default:
                style = AppMsg.STYLE_INFO;
                break;
        }
        // create {@link AppMsg} with specify type
        AppMsg appMsg = provided != null ? provided : AppMsg.makeText(getActivity(), msg, style);
        appMsg.setPriority(priority);
        if (mParent.isChecked()) {
            appMsg.setParent(mAltParent);
        } else {
            if (mBottom.isChecked()) {
                appMsg.setLayoutGravity(80);
            }
        }

        if (customAnimations) {
            appMsg.setAnimation(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
        appMsg.show();

    }

    private static int positionToPriority(int selectedItemPosition) {
        switch (selectedItemPosition) {
            case 0:
                return AppMsg.PRIORITY_HIGH;
            case 2:
                return AppMsg.PRIORITY_LOW;
            default:
                return AppMsg.PRIORITY_NORMAL;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // This is optional for 14+,
        // also you may want to call it at your later convenience, e.g. onDestroy
//        if (SDK_INT < ICE_CREAM_SANDWICH) {
        AppMsg.cancelAll(getActivity());
//        }
    }

    static class CancelAppMsg implements View.OnClickListener {
        private final AppMsg mAppMsg;

        CancelAppMsg(AppMsg appMsg) {
            mAppMsg = appMsg;
        }

        @Override
        public void onClick(View v) {
            mAppMsg.cancel();
        }
    }
}
