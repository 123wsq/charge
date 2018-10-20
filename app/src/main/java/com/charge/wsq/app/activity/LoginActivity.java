package com.charge.wsq.app.activity;

import android.os.Bundle;

import com.charge.wsq.app.R;
import com.charge.wsq.app.base.BaseActivity;
import com.charge.wsq.app.mvp.presenter.BasePresenter;

/**
 * 进入该页面首先验证微信是否已经授权登录过  如果则授权登录  如果已经登录过  则直接进入到主页面
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
