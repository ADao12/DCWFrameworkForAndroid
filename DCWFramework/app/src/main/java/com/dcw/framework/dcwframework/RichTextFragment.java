package com.dcw.framework.dcwframework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dcw.adaoframework.util.LinkTouchMovementMethod;
import com.dcw.adaoframework.util.RichTextBuilder;
import com.dcw.adaoframework.util.TouchableSpan;
import com.dcw.adaoframework.view.DCWAnnotation;
import com.dcw.adaoframework.view.annotation.InjectLayout;
import com.dcw.adaoframework.view.annotation.InjectView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
 * @create 15/3/10
 */
@InjectLayout(R.layout.fragment_rich_text)
public class RichTextFragment extends Fragment {
    private static final String TAG = "RichTextFragment";

    @InjectView(R.id.tv_content)
    private TextView mTVContent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View convertView = inflater.inflate(R.layout.fragment_rich_text, null);
        DCWAnnotation.inject(this, convertView);

        String text = "Google www \n电话:1234812121 \n邮件:sd1233333@qq.com  \n网址:百度 ";
        //给选中的文字添加链接和点击事件
//        setTextLinkedStringBuilder(mTVContent, text, new String[]{"Google", "www", "百度"});
//        new RichTextBuilder(getActivity()).append(text).addLinkForStr(mTVContent, new String[]{"Google", "www", "百度"}, new TouchableSpan.OnClickListener[]{new TouchableSpan.OnClickListener() {
//            @Override
//            public void onClick(String content) {
//                Toast.makeText(getActivity(), content, 0).show();
//                Log.e(TAG, content);
//            }
//        }}, "a", "b", null);
//
        Spannable ssb = new RichTextBuilder(getActivity()).append("abc").appendTouchableText("gle", 0, 3, new TouchableSpan.OnClickListener() {
            @Override
            public void onClick(String content) {
                Toast.makeText(getActivity(), content, 0).show();
                Log.e(TAG, content);
            }
        }, "a").append("abc").append("abc").appendTouchableText("gle", 0, 3, new TouchableSpan.OnClickListener() {
            @Override
            public void onClick(String content) {
                Toast.makeText(getActivity(), content, 0).show();
                Log.e(TAG, content);
            }
        }, "a").append("abcd", 1, 4).build();
//        Spannable ssb = new RichTextBuilder(getActivity()).append("abc").appendTouchableEdge(1, 4, new TouchableSpan.OnClickListener() {
//            @Override
//            public void onClick(String content) {
//                Toast.makeText(getActivity(), content, 0).show();
//                Log.e(TAG, content);
//            }
//        }, "a").build();
        mTVContent.setText(ssb);
        mTVContent.setMovementMethod(LinkTouchMovementMethod.getInstance());
        return convertView;
    }

    protected void setTextLinkedStringBuilder(TextView tv, CharSequence text, String[] keys, String... urls) {
        tv.setMovementMethod(LinkTouchMovementMethod.getInstance());//设置超链接
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        Pattern pattern = null;
        Matcher matcher = null;
        for (int i = 0; i < keys.length; i++) {
            try {
                pattern = Pattern.compile(keys[i], Pattern.CASE_INSENSITIVE);
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
//                return ssb;
                return;
            }
            matcher = pattern.matcher(ssb);
            while (matcher.find()) {

                TouchableSpan sp = new TouchableSpan(urls.length == 0 || urls[i] == null ? "" : urls[i], new TouchableSpan.OnClickListener() {
                    @Override
                    public void onClick(String content) {
                        Toast.makeText(getActivity(), content, 0).show();
                        Log.e(TAG, content);
                    }
                });

                ssb.setSpan(sp, matcher.start(), matcher.end(),
                        Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        tv.setText(ssb);
    }

}
