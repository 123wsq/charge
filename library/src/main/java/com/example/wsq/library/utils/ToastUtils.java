package com.example.wsq.library.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by wsq on 2018/1/11.
 */

public class ToastUtils {

    private static ToastUtils toastUtils;
    private static Context mContext;

    public ToastUtils(){

    }
    public static ToastUtils getInstance(Context context){

        if (toastUtils == null){
            mContext = context;
            toastUtils = new ToastUtils();
        }
        return toastUtils;
    }

    public  static void onToast(String msg){

        if (mContext == null){
            return;
        }
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }


    public static void onToast(View view){

        Toast toast = new Toast(mContext);
        toast.setView(view);
        toast.setDuration(1 * 1000);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public static void onToast(View view,int gravity){

        Toast toast = new Toast(mContext);
        toast.setView(view);
        toast.setDuration(1 * 1000);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }
}
