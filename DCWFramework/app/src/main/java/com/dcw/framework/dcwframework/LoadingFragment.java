package com.dcw.framework.dcwframework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dcw.adaoframework.view.DCWAnnotation;
import com.dcw.adaoframework.view.annotation.InjectLayout;
import com.dcw.adaoframework.view.annotation.InjectView;

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
@InjectLayout(R.layout.fragment_loading)
public class LoadingFragment extends Fragment{

//    @InjectView(R.id.tv_content)
//    private View mTVContent;
    @InjectView(R.id.ll_content)
    private View mLLparent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View convertView = inflater.inflate(R.layout.fragment_loading, null);
        DCWAnnotation.inject(this, convertView);
//        mTVContent.setText("ok");
//        final View loadingView = inflater.inflate(R.layout.loading_page2, null);
//        ViewGroup parent = (ViewGroup)mLLparent.getParent();
//        parent.removeView(mLLparent);
////        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
////        new FrameLayout.LayoutParams()
//        FrameLayout fl = new FrameLayout(getActivity());
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mLLparent.getLayoutParams());
////        params.leftMargin = ((ViewGroup.MarginLayoutParams)mLLparent.getLayoutParams()).leftMargin;
////        params.rightMargin = ((ViewGroup.MarginLayoutParams)mLLparent.getLayoutParams()).rightMargin;
////        params.topMargin = ((ViewGroup.MarginLayoutParams)mLLparent.getLayoutParams()).topMargin;
////        params.bottomMargin = ((ViewGroup.MarginLayoutParams)mLLparent.getLayoutParams()).bottomMargin;
//        fl.setLayoutParams(params);
//        parent.addView(loadingView, new ViewGroup.LayoutParams(mLLparent.getLayoutParams()));
//        parent.addView(mLLparent, new ViewGroup.LayoutParams(mLLparent.getLayoutParams()));
////        loadingView.setVisibility(View.GONE);

        ViewGroup parent = (ViewGroup)mLLparent.getParent();
        final View loadingView = inflater.inflate(R.layout.loading_page2, null);
        parent.addView(loadingView, parent.indexOfChild(mLLparent), mLLparent.getLayoutParams());
        loadingView.setLayoutParams(mLLparent.getLayoutParams());
        mLLparent.setVisibility(View.INVISIBLE);
        ViewTreeObserver vto = mLLparent.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                loadingView.getLayoutParams().height = mLLparent.getMeasuredHeight();
                loadingView.getLayoutParams().width = mLLparent.getMeasuredWidth();
                return true;
            }
        });

//        loadingView.setVisibility(View.GONE);
//        mLLparent.setVisibility(View.VISIBLE);
//        parent.removeView(mTVContent);
        return convertView;
    }
}
