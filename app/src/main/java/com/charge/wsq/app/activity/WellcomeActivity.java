package com.charge.wsq.app.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.charge.wsq.app.R;
import com.example.wsq.library.utils.DensityUtil;
import com.example.wsq.library.utils.IntentUtils;
import com.example.wsq.library.utils.NetworkUtils;
import com.example.wsq.library.utils.SharedTools;
import com.charge.wsq.app.adapter.WellcomeAdapter;
import com.charge.wsq.app.base.BaseActivity;
import com.charge.wsq.app.constant.Constant;
import com.charge.wsq.app.mvp.presenter.BasePresenter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WellcomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.layout_Indicator) LinearLayout layout_Indicator;
    @BindView(R.id.tv_start) TextView tv_start;
    @BindView(R.id.tv_come_in) TextView tv_come_in;
    @BindView(R.id.rl_first_layout)RelativeLayout rl_first_layout;
    @BindView(R.id.rl_layout)RelativeLayout rl_layout;

    private int drawables[] = {R.mipmap.wellcome_1, R.mipmap.wellcome_2, R.mipmap.wellcome_3, R.mipmap.wellcome_4};

    private List<View> mDate;
    private WellcomeAdapter mAdapter;

    private int curLen = 1;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_activity_app_wellcome;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        boolean isFirst = SharedTools.getInstance(this).onGetBoolean(Constant.ISFIRST);
        if (!isFirst){
            rl_first_layout.setVisibility(View.VISIBLE);
            getImages();
            drawIndicator();
            mAdapter = new WellcomeAdapter(this, mDate);
            viewPager.setAdapter(mAdapter);
            viewPager.addOnPageChangeListener(this);
        }else {

//            rl_layout.setVisibility(View.VISIBLE);
//            handler.postDelayed(runnable, 1000);
            onBreak();
            tv_start.setVisibility(View.GONE);
        }

    }

    public void getImages(){
        mDate=  new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0 ; i < drawables.length; i++){

            View view = inflater.inflate(R.layout.item_wellcome_layout, null);
            ImageView image= view.findViewById(R.id.iv_image);
            image.setBackgroundResource(drawables[i]);
            mDate.add(view);
        }

    }

    public  void drawIndicator(){

        for (int i = 0 ; i<mDate.size(); i++){
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DensityUtil.dp2px(this, 5), DensityUtil.dp2px(this, 5));
            params.leftMargin = DensityUtil.dp2px(this, 10);
            imageView.setLayoutParams(params);
            imageView.setImageResource(i==0 ? R.mipmap.image_indicator_selector : R.mipmap.image_indicator_default);
            layout_Indicator.addView(imageView);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tv_come_in.setVisibility(position == mDate.size()-1 ? View.VISIBLE : View.GONE);

        for (int i = 0 ;  i< mDate.size() ; i ++) {
            ImageView view = (ImageView) layout_Indicator.getChildAt(i);
            view.setImageResource(i==position ? R.mipmap.image_indicator_selector :R.mipmap.image_indicator_default);

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    Handler handler = new Handler(){};

    Runnable runnable = new Runnable() {

        @Override

        public void run() {

            curLen--;
            if (curLen == 0){
                handler.removeCallbacks(runnable);
                onBreak();
            }else{
                tv_start.setText(curLen+"s进入");
                handler.postDelayed(this, 1000);
            }
        }

    };


    @OnClick({R.id.tv_come_in, R.id.tv_start})
    public void onClick(View view){
        try {
            switch (view.getId()){
                case R.id.tv_come_in:
                    SharedTools.getInstance(WellcomeActivity.this).onPutData(Constant.ISFIRST, true);
                    break;
                case R.id.tv_start:
                    handler.removeCallbacks(runnable);
                    break;
            }
            onBreak();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onBreak(){
        try {

                // 转场动画(API16+)
                IntentUtils.onStartActivity(this, MainActivity.class, null);
                overridePendingTransition(R.anim.anim_activity_default_in, R.anim.anim_activity_default_out);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
