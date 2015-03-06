package com.dcw.framework.dcwframework;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dcw.adaoframework.view.DCWAnnotation;
import com.dcw.adaoframework.view.annotation.InjectLayout;
import com.dcw.adaoframework.view.annotation.InjectView;

import static android.widget.Toast.LENGTH_SHORT;

@InjectLayout(R.layout.main)
public class MainActivity extends FragmentActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @InjectView(value = R.id.tv_content, listeners = View.OnClickListener.class)
    private TextView mTVContent;

    @InjectView(value = R.id.list_of_things, listeners = AdapterView.OnItemClickListener.class)
    ListView listOfThings;
    private SimpleAdapter adapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DCWAnnotation.inject(this);
        mTVContent.setText("aaa");
        adapter = new SimpleAdapter(this);
        listOfThings.setAdapter(adapter);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new MainFragment()).commit();
    }

    @Override
    public void onClick(View view) {

        mTVContent.setText("clicked me.");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Toast.makeText(this, "You clicked: " + adapter.getItem(position), LENGTH_SHORT).show();
    }
}
