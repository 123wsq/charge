package com.charge.wsq.app.fragment.main;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.charge.wsq.app.R;
import com.example.wsq.library.tools.ViewSize;
import com.charge.wsq.app.base.BaseFragment;
import com.charge.wsq.app.constant.ResponseKey;
import com.charge.wsq.app.mvp.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class MenuFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

    public static final String TAG = MenuFragment.class.getName();

    public static final String INTERFACE_WITHP = TAG + _INTERFACE_WITHP;

    @BindView(R.id.rg_menu) RadioGroup rg_menu;
    @BindViews({R.id.rb_record, R.id.rb_my}) RadioButton[] rb_Radio;

    private int[] drawableId ={R.drawable.selector_menu_record, R.drawable.selector_menu_my};

    public static MenuFragment getInstance(){
        return new MenuFragment();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_menu;
    }

    @Override
    protected void initView() {
        for (int i = 0; i < drawableId.length; i++) {
            ViewSize.onSetRadioButtonSize(getContext(), rb_Radio[i], drawableId[i]);
        }

        rg_menu.setOnCheckedChangeListener(this);
        mFunctionsManage.invokeFunction(INTERFACE_WITHP, 1);


    }
    @OnClick({R.id.ll_curData})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_curData:
                mFunctionsManage.invokeFunction(INTERFACE_WITHP, 1);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        try{
        switch (checkedId){
            case R.id.rb_record:
                    mFunctionsManage.invokeFunction(INTERFACE_WITHP, 2);
                break;

            case  R.id.rb_my:
                    mFunctionsManage.invokeFunction(INTERFACE_WITHP, 3);
                break;
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
