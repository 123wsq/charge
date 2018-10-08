package com.example.wsq.library.utils;

import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewTools {

    public static void init(WebView webView, boolean isCache){

        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        //支持插件
//        webSettings.setPluginsEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        /**
         * LOAD_DEFAULT  默认加载方式，使用这种方式，会实现快速前进后退，在同一个标签打开几个网页后，关闭网络时，可以通过前进后退来切换已经访问过的数据，同时新建网页需要网络
         * LOAD_NO_CACHE
         *  LOAD_NORMAL  这个方式跟LOAD_NO_CACHE方式相同，不使用缓存，如果没有网络，即使以前打开过此网页也不会使用以前的网页
         *
         *  LOAD_CACHE_ELSE_NETWORK 这个方式不论如何都会从缓存中加载，除非缓存中的网页过期，出现的问题就是打开动态网页时，不能时时更新，会出现上次打开过的状态，除非清除缓存
         *  LOAD_CACHE_ONLY  这个方式只是会使用缓存中的数据，不会使用网络
         *
         *  影响缓存模式的两个http头是If-None-Match和If-Modified-Since，遇到这两个http头，浏览器会把缓存模式改为LOAD_NO_CACHE方式
         */
        if (isCache){
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //
        }else {
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //
        }

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }




}
