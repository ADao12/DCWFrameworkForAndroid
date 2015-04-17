package com.dcw.framework.dcwframework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcw.adaoframework.view.DCWAnnotation;
import com.dcw.adaoframework.view.annotation.InjectLayout;
import com.dcw.adaoframework.view.annotation.InjectView;
import com.dcw.framework.dcwframework.widget.AlignWrapTextView;

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
 * @create 15/4/16
 */
@InjectLayout(R.layout.fragment_align_wrap)
public class AlignWrapFragment extends Fragment {

    @InjectView(R.id.tv_content)
    AlignWrapTextView mTvContent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_align_wrap, null);
        DCWAnnotation.inject(this, convertView);
        return convertView;
    }
}
