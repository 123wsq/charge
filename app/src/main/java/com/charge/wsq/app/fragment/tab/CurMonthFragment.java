package com.charge.wsq.app.fragment.tab;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.charge.wsq.app.R;
import com.charge.wsq.app.adapter.ChargeChartPageAdapter;
import com.charge.wsq.app.base.BaseFragment;
import com.charge.wsq.app.constant.FragmentIDs;
import com.charge.wsq.app.mvp.presenter.DefaultPresenter;
import com.charge.wsq.app.mvp.view.DefaultView;
import com.charge.wsq.app.tools.ChartViewLayout;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CurMonthFragment extends BaseFragment<DefaultView, DefaultPresenter<DefaultView>> implements DefaultView{

    public static final String TAG = CurMonthFragment.class.getName();

    @BindView(R.id.ll_add_data) LinearLayout ll_add_data;
    @BindView(R.id.tv_title) TextView tv_title;
    @BindView(R.id.magic_indicator) MagicIndicator magic_indicator;
    @BindView(R.id.view_pager) ViewPager view_pager;

    private ChartViewLayout chartViewLayout;
    private List<String> mPageTitles;
    private List<View> mPageViews;
    private ChargeChartPageAdapter mPageAdapter;




    public static CurMonthFragment getInstance() {
        return new CurMonthFragment();
    }


    @Override
    protected DefaultPresenter<DefaultView> createPresenter() {
        return new DefaultPresenter<>();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_tab_cur_month;
    }

    @Override
    protected void initView() {
        ll_add_data.setVisibility(View.VISIBLE);
        Calendar calendar = Calendar.getInstance();

        tv_title.setText(calendar.get(Calendar.MONTH)+"月账单");

        onInitData();
        onInitMagicIndicator();
    }


    @OnClick({R.id.ll_add_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_add_data:
                mFunctionsManage.invokeFunction(_INTERFACE_WITHP, FragmentIDs.F_AddChargeFragment,"");
                break;
        }
    }


    private void onInitData(){
        mPageViews = new ArrayList<>();
        mPageTitles = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mPageTitles.add("当前"+i);
            ChartViewLayout chartViewLayout = new ChartViewLayout(getActivity(), i%2);
            mPageViews.add(chartViewLayout.onChart());
        }
        mPageAdapter = new ChargeChartPageAdapter(getActivity(), mPageViews);
        view_pager.setAdapter(mPageAdapter);
    }
    private void onInitMagicIndicator(){
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mPageTitles == null ? 0 : mPageTitles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#88ffffff"));
                colorTransitionPagerTitleView.setSelectedColor(Color.WHITE);
                colorTransitionPagerTitleView.setText(mPageTitles.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view_pager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#40c4ff"));
                return indicator;
            }
        });
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magic_indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magic_indicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magic_indicator.onPageScrollStateChanged(state);
            }
        });

        //设置背景色
        magic_indicator.setBackgroundColor(Color.parseColor("#455a64"));
        magic_indicator.setNavigator(commonNavigator);

        ViewPagerHelper.bind(magic_indicator, view_pager);
    }

    @Override
    public void responseData(Map<String, Object> data) {

    }
}
