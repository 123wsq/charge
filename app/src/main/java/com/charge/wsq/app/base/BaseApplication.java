package com.charge.wsq.app.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;

import com.example.wsq.library.utils.SharedTools;
import com.example.wsq.library.utils.ToastUtils;
import com.example.wsq.library.utils.Utils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.charge.wsq.app.fragment.main.MainFragment;
import com.charge.wsq.app.fragment.tab.RecordFragment;
import com.charge.wsq.app.fragment.tab.MyFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import org.litepal.LitePalApplication;

import java.util.HashMap;
import java.util.Map;

public class BaseApplication extends LitePalApplication {

    public static Map<String, Fragment> fragmentMap = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();


        Utils.init(this);
        SharedTools.getInstance(this);
        ToastUtils.getInstance(this);
        onInitLog();
        onInitUmeng();
        onAddFragment();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    static {

        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {

            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {

//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色

                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header

//                return new TaurusHeader(context);

            }

        });

        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {

            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {

                //指定为经典Footer，默认是 BallPulseFooter

                return new ClassicsFooter(context).setDrawableSize(20);

            }

        });

    }

    private void onInitUmeng() {
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(false);

        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);


        //场景类型设置接口
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);


        MobclickAgent.setCatchUncaughtExceptions(true);
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         *
         * 正式  5b2b0a36f43e4868eb00000e
         *
         * 测试 5b207adfa40fa345ee00008c
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5b651e538f4a9d1d1d0005d0");
        /*
        注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，UMConfigure.init调用中appkey和channel参数请置为null）。
        */
//        UMConfigure.init(this, "5b207adfa40fa345ee00008c", "ceshi", UMConfigure.DEVICE_TYPE_PHONE, "");
    }
    private void onInitLog(){
        Logger.init("mytag")    //LOG TAG默认是PRETTYLOGGER
                .methodCount(3)                 // 决定打印多少行（每一行代表一个方法）默认：2
                .hideThreadInfo()               // 隐藏线程信息 默认：显示
                .logLevel(LogLevel.FULL)        // 是否显示Log 默认：LogLevel.FULL（全部显示）
                .methodOffset(2)                // 默认：0
                ; //可以自己构造适配器默认：AndroidLogAdapter


    }

    private void onAddFragment() {
        fragmentMap.put(MainFragment.TAG, MainFragment.getInstance());

    }
}
