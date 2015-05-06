package com.dcw.app.rating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dcw.app.rating.db.bean.Cache;

import java.util.List;

/**
 * @author JiaYing.Cheng
 * @version 1.0
 * @email adao12.vip@gmail.com
 * @create 15/5/5
 */
public class BoxListAdapter extends ArrayAdapter<Cache> {

    private LayoutInflater inflater;
    private Context context;

    public BoxListAdapter(Context context) {
        this(context, null);
    }

    public BoxListAdapter(Context context, List<Cache> dataList) {
        super(context, 0);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (Cache box : dataList) {
            add(box);
        }
    }

    public void updateData(List<Cache> boxList) {
        this.clear();
        for (Cache aBoxList : boxList) {
            add(aBoxList);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_box, null);
            viewHolder = new ViewHolder();
            viewHolder.root = (LinearLayout) convertView.findViewById(R.id.boxItem);
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tvItemId);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvSize = (TextView) convertView.findViewById(R.id.tvSize);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        editBackground(position, viewHolder);
        fillViewWithData(position, viewHolder);

        return convertView;
    }

    private void editBackground(int position, ViewHolder viewHolder) {
        if (position % 2 == 0) {
            viewHolder.root.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        } else {
            viewHolder.root.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
        }
    }

    private void fillViewWithData(int position, ViewHolder viewHolder) {
        viewHolder.tvId.setText(context.getString(R.string.tv_label_item_id) + " " + getItem(position).getKey());
        viewHolder.tvName.setText(context.getString(R.string.tv_label_box_name) + " " + getItem(position).getKey());
        viewHolder.tvSize.setText(context.getString(R.string.tv_label_box_size) + " " + getItem(position).getGroupId());
        viewHolder.tvDescription.setText(context.getString(R.string.tv_label_box_description) + " " + getItem(position).getExpireTime());
    }

    static class ViewHolder {
        LinearLayout root;
        TextView tvId;
        TextView tvName;
        TextView tvSize;
        TextView tvDescription;
    }
}