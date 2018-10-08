package com.example.wsq.library.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.wsq.library.bean.CityInfoBean;
import com.example.wsq.library.db.DBHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/8 0008.
 */

public class CityDao {
    private DBHelper myDbHelper;

    public CityDao(Context context, String pkg){

        myDbHelper = new DBHelper(context, pkg);
        try {
            myDbHelper.createDataBase();
            myDbHelper.openDataBase();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有的省份
     * @return
     */
    public synchronized List<CityInfoBean> getData(int parent_id){
        List<CityInfoBean> listCity = new ArrayList<>();

        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor c = db.query(CityInfoBean.TABLE_NAME, new String[]{CityInfoBean.ID, CityInfoBean.NAME, CityInfoBean.PARENT_ID, CityInfoBean.AREA_CODE}, null, null, null, null, null);
        while (c.moveToNext()){
            CityInfoBean bean = new CityInfoBean();
            bean.setId(c.getInt(c.getColumnIndex(CityInfoBean.ID)));
//            bean.setArea_code(c.getString(c.getColumnIndex(CityInfoBean.AREA_CODE)));
            bean.setName(c.getString(c.getColumnIndex(CityInfoBean.NAME)));
//            bean.setLevel(c.getInt(c.getColumnIndex(CityInfoBean.LEVEL)));
//            bean.setCity_code(c.getString(c.getColumnIndex(CityInfoBean.CITY_CODE)));
//            bean.setCenter(c.getString(c.getColumnIndex(CityInfoBean.CENTER)));
            bean.setParent_id(c.getInt(c.getColumnIndex(CityInfoBean.PARENT_ID)));
            bean.setArea_code(c.getString(c.getColumnIndex(CityInfoBean.AREA_CODE)));
            listCity.add(bean);
        }

        return  listCity;
    }


}
