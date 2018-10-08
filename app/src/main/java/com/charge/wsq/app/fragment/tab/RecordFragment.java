package com.charge.wsq.app.fragment.tab;


import android.view.View;
import android.widget.TextView;

import com.charge.wsq.app.R;
import com.charge.wsq.app.base.BaseFragment;
import com.charge.wsq.app.mvp.presenter.BasePresenter;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RecordFragment extends BaseFragment{

    public static final String TAG = RecordFragment.class.getName();



    public static RecordFragment getInstance() {
        return new RecordFragment();
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_home;
    }

    @Override
    protected void initView() {
    }


    @OnClick({R.id.tv_single_permission, R.id.tv_multiple_permission})
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }


}
