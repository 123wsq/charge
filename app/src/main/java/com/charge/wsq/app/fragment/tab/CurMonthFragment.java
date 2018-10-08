package com.charge.wsq.app.fragment.tab;


import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.charge.wsq.app.R;
import com.charge.wsq.app.base.BaseFragment;
import com.charge.wsq.app.constant.FragmentIDs;
import com.charge.wsq.app.mvp.presenter.BasePresenter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class CurMonthFragment extends BaseFragment{

    public static final String TAG = CurMonthFragment.class.getName();

    @BindView(R.id.ll_add_data) LinearLayout ll_add_data;
    @BindView(R.id.tv_title) TextView tv_title;
    @BindView(R.id.bc_BarChart) BarChart bc_BarChart;
    protected Typeface mTfLight;
    public static CurMonthFragment getInstance() {
        return new CurMonthFragment();
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_cur_month;
    }

    @Override
    protected void initView() {
        ll_add_data.setVisibility(View.VISIBLE);
        Calendar calendar = Calendar.getInstance();

        tv_title.setText(calendar.get(Calendar.MONTH)+"月账单");
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        onInitChart();


    }


    @OnClick({R.id.ll_add_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_add_data:
                mFunctionsManage.invokeFunction(_INTERFACE_WITHP, FragmentIDs.F_AddChargeFragment,"");
                break;
        }
    }

    private void onInitChart(){

    }

    private void onSetData(){


    }

}
