package com.charge.wsq.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class WellcomeAdapter extends PagerAdapter {



    private Context mContext;
    private int drawables[];
    private List<View> mData;

    public WellcomeAdapter(Context context, List<View> list){

        this.mContext = context;
        this.mData = list;
    }

    @Override

    public int getCount() {

        return mData.size();
    }



    @Override

    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView(mData.get(position));
    }



    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = mData.get(position);
        container.addView(view);
        return view;

    }
}
