package com.example.wsq.library.utils;


import com.example.wsq.library.BuildConfig;

public class LogUtils {


    public static void d(String msg){

        if (BuildConfig.DEBUG){
            System.out.println("");
            int rows = msg.length() / 1024 + 1;
            for (int i = 0; i < rows; i++) {
                if (msg.substring(i*1024).length()>1024) {
                    System.out.println(msg.substring(i * 1024, (i + 1) * 1024));
                }else {
                    System.out.println(msg.substring(i * 1024));
                }
            }


        }
    }
}
