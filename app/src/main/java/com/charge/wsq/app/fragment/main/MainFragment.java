package com.charge.wsq.app.fragment.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.charge.wsq.app.fragment.tab.CurMonthFragment;
import com.charge.wsq.app.fragment.tab.HomeFragment;
import com.example.wsq.library.tools.status.AppStatus;
import com.example.wsq.library.utils.SharedTools;
import com.charge.wsq.app.R;
import com.charge.wsq.app.base.BaseFragment;
import com.charge.wsq.app.constant.ResponseKey;
import com.charge.wsq.app.fragment.tab.RecordFragment;
import com.charge.wsq.app.fragment.tab.MyFragment;
import com.charge.wsq.app.mvp.presenter.BasePresenter;
import com.charge.wsq.app.mvp.view.DefaultView;
import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;
import java.util.Map;

/**
 * Created by wsq on 2018/1/23.
 */

public class MainFragment extends BaseFragment {


    public static final String TAG = MainFragment.class.getName();

    private Fragment fragments[] = {MenuFragment.getInstance(),
            HomeFragment.getInstance(),
            CurMonthFragment.getInstance(),
            RecordFragment.getInstance(),
            MyFragment.getInstance(),
            MyFragment.getInstance()};
    private String[] tags = {MenuFragment.TAG, HomeFragment.TAG, CurMonthFragment.TAG,RecordFragment.TAG, MyFragment.TAG, MyFragment.TAG};

    private FragmentManager fragmentManager;
    private Fragment curFragment;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_main;
    }

    @Override
    public void initView() {

        fragmentManager = getActivity().getSupportFragmentManager();
        onEnter(R.id.ll_menu, fragments[0], tags[0]);

    }

    public void onEnter(int resource, Fragment fragment,  String tag) {

        getActivity().getSupportFragmentManager().beginTransaction().replace(resource, fragment, tag).commit();
    }

    /**
     * @param fragment
     * @param tag
     * @param param    传递的参数
     * @param isBack   是否支持返回
     */
    private void onEnter(Fragment fragment, String tag, Bundle param, boolean isBack) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();

        if (curFragment != null) fTransaction.hide(curFragment);

        if (!fragment.isAdded()) {
            if (param != null) fragment.setArguments(param);

            fTransaction.add(R.id.ll_content, fragment, tag);
            if (isBack) fTransaction.addToBackStack(tag);
            fTransaction.show(fragment).commit();
        } else {
            fTransaction.show(fragment).commit();
        }
        curFragment = fragment;
    }


    /**
     * 点击菜单切换tab
     *
     * @param position
     */
    public void onShowFragnentContent(int position) {
        onEnter(fragments[position], tags[position], null, false);

    }


}
