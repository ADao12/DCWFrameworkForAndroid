package com.dcw.framework.dcwframework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dcw.adaoframework.view.DCWAnnotation;
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
 * @create 15/2/13
 */
public class SimpleAdapter extends BaseAdapter {
    private static final String[] CONTENTS =
            "The quick brown fox jumps over the lazy dog".split(" ");

    private final LayoutInflater inflater;

    public SimpleAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override public int getCount() {
        return CONTENTS.length;
    }

    @Override public String getItem(int position) {
        return CONTENTS[position];
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.simple_list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        String word = getItem(position);
        holder.word.setText("Word: " + word);
        holder.length.setText("Length: " + word.length());
        holder.position.setText("Position: " + position);
        // Note: don't actually do string concatenation like this in an adapter's getView.

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.word)
        TextView word;
        @InjectView(R.id.length) TextView length;
        @InjectView(R.id.position) TextView position;

        ViewHolder(View view) {
            DCWAnnotation.inject(this, view);
        }
    }
}
