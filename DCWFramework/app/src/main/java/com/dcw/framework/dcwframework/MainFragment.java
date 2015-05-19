package com.dcw.framework.dcwframework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dcw.framework.view.DCWAnnotation;
import com.dcw.framework.view.annotation.InjectLayout;
import com.dcw.framework.view.annotation.InjectView;

import static android.widget.Toast.LENGTH_SHORT;

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
 * @create 15/2/25
 */
@InjectLayout(R.layout.fragment_main)
public class MainFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    @InjectView(value = R.id.tv_content, listeners = View.OnClickListener.class)
    private TextView mTVContent;

    @InjectView(value = R.id.list_of_things, listeners = AdapterView.OnItemClickListener.class)
    ListView mListView;
    private SimpleAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View convertView = inflater.inflate(R.layout.fragment_main, null);
        DCWAnnotation.inject(this, convertView);
        mTVContent.setText("aaa");
        mAdapter = new SimpleAdapter(getActivity());
        mListView.setAdapter(mAdapter);

        return convertView;
    }

    @Override
    public void onClick(View view) {

        mTVContent.setText("clicked me.");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Toast.makeText(getActivity(), "You clicked: " + mAdapter.getItem(position), LENGTH_SHORT).show();
    }
}
