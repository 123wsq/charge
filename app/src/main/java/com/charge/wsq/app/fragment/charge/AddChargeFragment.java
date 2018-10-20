package com.charge.wsq.app.fragment.charge;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.charge.wsq.app.R;
import com.charge.wsq.app.base.BaseFragment;
import com.charge.wsq.app.mvp.presenter.BasePresenter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class AddChargeFragment extends BaseFragment implements TextWatcher {

    public static final String TAG = AddChargeFragment.class.getName();

    @BindView(R.id.tv_title) TextView tv_title;
    @BindView(R.id.et_remarks) EditText et_remarks;
    @BindView(R.id.tv_font_count) TextView tv_font_count;

    private int max_count =  100;


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
        et_remarks.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max_count)});
        et_remarks.addTextChangedListener(this);
    }


    @OnClick({R.id.ll_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_back:
                mFunctionsManage.invokeFunction(INTERFACE_BACK,"");
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        tv_font_count.setText("还剩 "+ editable.toString().length() +" / " +max_count);
    }
}
