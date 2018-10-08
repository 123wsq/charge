package com.example.wsq.library.view.city;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.example.wsq.library.R;
import com.example.wsq.library.bean.CityInfoBean;
import com.example.wsq.library.dao.CityDao;
import com.example.wsq.library.utils.AppManager;
import com.example.wsq.library.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class CityWheelPopup extends PopupWindow{

    private Activity mContext;
    private OnCityResultCallBack mCallBack;
    private View popupView;
    private View view_background;

    private WheelView wv_province, wv_city, wv_county;
    private TextView tv_popup_title, tv_cancel, tv_ok;


    private List<CityInfoBean> mData; //所有的数据
    private List<CityInfoBean> mListProvince; //省
    private List<CityInfoBean> mListCity;  //市
    private List<CityInfoBean> mListCounty;  //县区

    private CityInfoBean curProvince;
    private CityInfoBean curCity;
    private CityInfoBean curCounty;

    private CityDao cityDao;


    public CityWheelPopup(Activity context, OnCityResultCallBack cityResultCallBack, View view){
        this.mContext = context;
        this.mCallBack = cityResultCallBack;
        this.view_background = view;

        popupView = LayoutInflater.from(mContext).inflate(R.layout.layout_popup_wheel_city,null, false);

        cityDao = new CityDao(mContext, AppManager.getAppPackageName());
        onInitWheelView();
        initPopup();
    }

    public void initPopup(){

        int w = mContext.getResources().getDisplayMetrics().widthPixels;
        int h = mContext.getResources().getDisplayMetrics().heightPixels;
        // 设置按钮监听
        // 设置SelectPicPopupWindow的View
        this.setContentView(popupView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth((int)(w*0.9));
        //
        // 设置SelectPicPopupWindow弹出窗体的高
        int height = DensityUtil.dp2px(mContext, 45+30*6 + 20 +40);
//        if (null != mData) {
//            if (h / 2 <= DensityUtil.dp2px(mContext, (mData.size() * 50) + 90 + 30)) {
//                height = h / 2;
//            } else {
//                height = DensityUtil.dp2px(mContext, (mData.size() * 50) + 90+ 30);
//            }
//        }else{
//            height = h/2;
//        }
        this.setHeight(height);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.style_pop);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);

//        this.setOnDismissListener(new PoponDismissListener());

        popupView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

//					dismiss();

                return false;
            }
        });

    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
//        backgroundAlpha(0.5f);
        view_background.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        view_background.setVisibility(View.GONE);
//        backgroundAlpha(1f);
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha) {


        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        if (bgAlpha == 1) {
            mContext.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为 红米米手机上半透明效果无效的bug

            mContext.getWindow().setAttributes(lp);
        }
    }

    private void onInitWheelView(){

        wv_province = popupView.findViewById(R.id.wv_province);
        wv_city = popupView.findViewById(R.id.wv_city);
        wv_county = popupView.findViewById(R.id.wv_county);
        wv_province.setCyclic(false);
        wv_city.setCyclic(false);
        wv_county.setCyclic(false);

        tv_popup_title = popupView.findViewById(R.id.tv_popup_title);
        tv_cancel = popupView.findViewById(R.id.tv_cancel);
        tv_ok = popupView.findViewById(R.id.tv_ok);


        wv_province.setOnItemSelectedListener(new OnItemSelectedListener() {


            @Override
            public void onItemSelected(int index) {

                curProvince = mListProvince.get(index);
                onGetCityData(curProvince.getId());

            }
        });
        wv_city.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
               if (mListCity.size() ==0 ||mListCity.size() == index || index < 0){
                   return;
               }
                curCity = mListCity.get(index);
                onGetCountyData(curCity.getId());

            }
        });
        wv_county.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (mListCounty.size() ==0 || index < 0){
                    return;
                }
                curCounty = mListCounty.get(index);
                tv_popup_title.setText(curProvince.getName()+"-"+curCity.getName()+"-"+curCounty.getName());
            }
        });

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack!= null)
                mCallBack.onCallBack(curProvince, curCity, curCounty);
                dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        onInitData();
        onGetProvince();
    }

    /**
     * 初始化数据
     */
    private void onInitData(){

        mData = new ArrayList<>();
        mListProvince = new ArrayList<>();
        mListCity = new ArrayList<>();
        mListCounty = new ArrayList<>();

        mData.addAll(cityDao.getData(-1));
    }

    /**
     * 获取省
     */
    private void onGetProvince(){

        for (int i = 0; i < mData.size(); i++) {
            int parent_id = mData.get(i).getParent_id();
            if (parent_id ==-1){
                mListProvince.add(mData.get(i));
            }
        }
        wv_province.setAdapter( new CityWheelAdapter(mListProvince));
        curProvince = mListProvince.get(0);

        onGetCityData(curProvince.getId());

    }

    /**
     * 获取城市
     */
    private synchronized void onGetCityData(int parent_id){

        mListCity.clear();
        for (int i = 0; i < mData.size(); i++) {
            int id = mData.get(i).getParent_id();
            if (id == parent_id){
                mListCity.add(mData.get(i));
            }
        }
        wv_city.setAdapter( new CityWheelAdapter(mListCity));

        if (mListCity.size()>0) {
            curCity = mListCity.get(0);
            onGetCountyData(curCity.getId());
        }else{
            tv_popup_title.setText(curProvince.getName());
        }
    }

    /**
     * 获取县区
     * @param parent_id
     */
    private synchronized void onGetCountyData(int parent_id){

        mListCounty.clear();
        for (int i = 0; i < mData.size(); i++) {
            int id = mData.get(i).getParent_id();
            if (id == parent_id){
                mListCounty.add(mData.get(i));
            }
        }

        wv_county.setAdapter( new CityWheelAdapter(mListCounty));

        if (mListCounty.size()>0) {
            curCounty = mListCounty.get(0);
            tv_popup_title.setText(curProvince.getName() + "-" + curCity.getName() + "-" + curCounty.getName());
        }else {
            tv_popup_title.setText(curProvince.getName() + "-" + curCity.getName() );
        }
    }




}
