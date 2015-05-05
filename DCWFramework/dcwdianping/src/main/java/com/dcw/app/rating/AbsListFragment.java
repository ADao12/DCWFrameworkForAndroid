package com.dcw.app.rating;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dcw.adaoframework.view.DCWAnnotation;
import com.dcw.adaoframework.view.annotation.InjectView;
import com.dcw.app.rating.app.RatingApplication;
import com.dcw.app.rating.db.bean.Box;
import com.dcw.app.rating.db.dao.BoxDao;
import com.dcw.app.rating.ui.adapter.BaseFragmentWrapper;
import com.dcw.app.rating.util.TaskExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiaYing.Cheng
 * @version 1.0
 * @email adao12.vip@gmail.com
 * @create 15/5/5
 */
public class AbsListFragment extends BaseFragmentWrapper implements ICreateSequence{

    @InjectView(R.id.lv_list)
    private ListView mLvBoxs;

    private List<Box> mBoxs;

    @Override
    public Class getHostActivity() {
        return MainActivity.class;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.list_fragment, null);
            loadUI();
            loadData();
            setListeners();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void loadUI() {
        DCWAnnotation.inject(this, mRootView);
    }

    @Override
    public void loadData() {
        List<Box> boxes = new ArrayList<Box>();
        for (int i = 0; i < 10; i++) {
            Box entity = new Box();
            entity.setId((long)i);
            entity.setDescription("lalalallalalalal");
            entity.setName("box" + i % 2);
            entity.setSlots(i);
            boxes.add(entity);
        }
        getBoxDao().insertOrReplaceInTx(boxes);

        TaskExecutor.scheduleTask(50, new Runnable() {
            @Override
            public void run() {
                mBoxs = getBoxDao().loadAll();
                TaskExecutor.runTaskOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initUI();
                    }
                });
            }
        });
    }

    @Override
    public void initUI() {
        mLvBoxs.setAdapter(new BoxListAdapter(getActivity(), mBoxs));
    }

    @Override
    public void setListeners() {

    }

    private BoxDao getBoxDao() {
        return ((RatingApplication)getActivity().getApplication()).getDaoSession().getBoxDao();
    }
}
