package com.charge.wsq.app.fragment.tab;


import android.view.View;

import com.charge.wsq.app.R;
import com.charge.wsq.app.base.BaseFragment;
import com.charge.wsq.app.mvp.presenter.BasePresenter;
import com.charge.wsq.app.mvp.view.BaseView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment{

    public static final String TAG = HomeFragment.class.getName();

    @BindView(R.id.banner) Banner banner;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    protected BasePresenter<BaseView> createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_tab_home;
    }

    @Override
    protected void initView() {

        onInitBannerData();
    }




    @OnClick({})
    public void onClick(View view) {

        switch (view.getId()) {

        }
    }

    private void onInitBannerData(){
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("http://pic.chinadaily.com.cn/img/attachement/jpg/site1/20181017/d8cb8a14fb901d30af4c01.jpg");
        imageUrls.add("http://pic.chinadaily.com.cn/img/attachement/jpg/site1/20181017/d8cb8a14fb901d30af4d03.jpg");
        imageUrls.add("http://i2.chinanews.com/simg/hd/2018/10/16/3e7a34ec972544a8b1ada8afdfca4690.jpg");
        imageUrls.add("http://images.tmtpost.com/uploads/images/2018/10/20181017074948121.png");
        List<String> imageLink = new ArrayList<>();
        onInitBanner(banner,imageUrls, null, imageLink);
    }
}
