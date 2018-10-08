package com.charge.wsq.app.fragment.charge;

import android.view.View;
import android.widget.TextView;

import com.charge.wsq.app.R;
import com.charge.wsq.app.base.BaseFragment;
import com.charge.wsq.app.mvp.presenter.BasePresenter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class AddChargeFragment extends BaseFragment {

    public static final String TAG = AddChargeFragment.class.getName();

    @BindView(R.id.tv_title) TextView tv_title;

    public static AddChargeFragment getInstance(){
        return new AddChargeFragment();
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_add_charge;
    }

    @Override
    protected void initView() {

        tv_title.setText("添加账单");
    }


    @OnClick({R.id.ll_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_back:
                mFunctionsManage.invokeFunction(INTERFACE_BACK,"");
                break;
        }
    }
}
