package com.dcw.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.dcw.framework.adaoframework.R;

/**
 * 记载的转圈圈
 * Created by cc on 14-4-29.
 */
public class LoadingView extends ImageView {
    private Context mContext;

    public LoadingView(Context context) {
        super(context);
        mContext = context;
        initAnimation();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initAnimation();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initAnimation();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(getVisibility() == View.VISIBLE) {
            initAnimation();
        }
    }

    private void initAnimation(){
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                mContext, R.anim.animation_loading);
        // 使用ImageView显示动画
        startAnimation(hyperspaceJumpAnimation);
    }

    @Override
    public void setVisibility(int visibility) {
        if(View.VISIBLE == visibility){
            initAnimation();
        } else {
            clearAnimation();
        }
        
        super.setVisibility(visibility);
    }
}
