package com.dcw.framework.dcwframework;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

import com.dcw.framework.view.DCWAnnotation;
import com.dcw.framework.view.annotation.InjectLayout;

import java.lang.reflect.Field;

@InjectLayout(R.layout.main)
public class MainActivity extends FragmentActivity {

//    @InjectView(value = R.id.tv_content, listeners = View.OnClickListener.class)
//    private TextView mTVContent;
//
//    @InjectView(value = R.id.list_of_things, listeners = AdapterView.OnItemClickListener.class)
//    ListView listOfThings;
//    private SimpleAdapter adapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DCWAnnotation.inject(this);
//        mTVContent.setText("aaa");
//        adapter = new SimpleAdapter(this);
//        listOfThings.setAdapter(adapter);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new AlignWrapFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void getOverflowMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onClick(View view) {
//
//        mTVContent.setText("clicked me.");
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//
//        Toast.makeText(this, "You clicked: " + adapter.getItem(position), LENGTH_SHORT).show();
//    }
}
