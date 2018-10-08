package com.example.wsq.library.view.city;

import com.contrarywind.adapter.WheelAdapter;
import com.example.wsq.library.bean.CityInfoBean;

import java.util.List;

public class CityWheelAdapter implements WheelAdapter{

    private List<CityInfoBean> mData;
    public CityWheelAdapter(List<CityInfoBean> list){
        this.mData = list;
    }
    @Override
    public int getItemsCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int index) {

        if (index >= 0 && index < mData.size()) {
            return mData.get(index).getName();
        }
        return "";
    }

    @Override
    public int indexOf(Object o) {
        return mData.indexOf(o);
    }
}
