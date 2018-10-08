package com.charge.wsq.app.fragment.tab;


import android.view.View;
import com.charge.wsq.app.R;
import com.charge.wsq.app.base.BaseFragment;
import com.charge.wsq.app.mvp.presenter.BasePresenter;
import com.charge.wsq.app.mvp.view.BaseView;
import java.util.Map;
import butterknife.OnClick;

public class MyFragment extends BaseFragment{

    public static final String TAG = MyFragment.class.getName();


    public static MyFragment getInstance() {
        return new MyFragment();
    }

    @Override
    protected BasePresenter<BaseView> createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_main;
    }

    @Override
    protected void initView() {
    }




    @OnClick({})
    public void onClick(View view) {

        switch (view.getId()) {

        }
    }
}
