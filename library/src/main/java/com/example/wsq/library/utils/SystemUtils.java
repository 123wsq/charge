package com.example.wsq.library.utils;

import android.annotation.SuppressLint;
import android.telephony.TelephonyManager;

public class SystemUtils {

    @SuppressLint("MissingPermission")
    public static String getIMEI(){
        TelephonyManager manager = (TelephonyManager) Utils.getApp().
                getSystemService(Utils.getApp().TELEPHONY_SERVICE);
        return manager.getDeviceId();
    }
}
