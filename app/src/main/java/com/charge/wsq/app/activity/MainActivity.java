package com.charge.wsq.app.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.charge.wsq.app.R;
import com.charge.wsq.app.base.BaseActivity;
import com.charge.wsq.app.base.BaseApplication;
import com.charge.wsq.app.base.BaseFragment;
import com.charge.wsq.app.constant.FragmentIDs;
import com.charge.wsq.app.constant.ResponseKey;
import com.charge.wsq.app.fragment.charge.AddChargeFragment;
import com.charge.wsq.app.fragment.main.MainFragment;
import com.charge.wsq.app.fragment.main.MenuFragment;
import com.charge.wsq.app.mvp.presenter.DefaultPresenter;
import com.example.wsq.library.struct.FunctionWithParamOnly;
import com.example.wsq.library.struct.FunctionsManage;
import com.example.wsq.library.tools.status.AppStatus;
import com.example.wsq.library.utils.SharedTools;
import com.example.wsq.library.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private Fragment curFragment;
    private FragmentManager fragmentManager;
    private List<Fragment> mListFragment;
    private int fragmentId = 0;
    private int menuId = 0;
    public static String[] clearUserInfo = {ResponseKey.token, ResponseKey.name, ResponseKey.mobile,
            ResponseKey.password, ResponseKey.avatar};


    @Override
    protected DefaultPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mListFragment = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();

//        if (!NetworkUtils.isConnected()) {
//            onShowDialog("提示", "请检查您的网络", "确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    NetworkUtils.openWirelessSettings();
//                    dialog.dismiss();
//                }
//            });
//        }

        if (savedInstanceState != null && savedInstanceState.containsKey(ResponseKey.F_TAG)) {
            String[] tags = savedInstanceState.getStringArray(ResponseKey.F_TAG);
            menuId = savedInstanceState.getInt(ResponseKey.MENU_POI);
            for (int i = 0; i < tags.length; i++) {
                Message msg = new Message();
                msg.obj = tags[i];
                handler.sendMessageDelayed(msg, 500 + tags.length * 10);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt(ResponseKey.MENU_POI, menuId);
        onEnter(MainFragment.getInstance(), MainFragment.TAG, bundle, false);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String tag = (String) msg.obj;
            try {
                Map<String, Fragment> fragments = BaseApplication.fragmentMap;
                if (fragments.containsKey(tag)) {
                    Fragment fragment = fragments.get(tag);
                    onEnter(fragment, tag, true);
                } else {
                    throw new Exception("未知的tag:" + tag);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    };

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(ResponseKey.MENU_POI, menuId);
        String[] tags = new String[mListFragment.size() - 1];

        for (int i = 1; i < mListFragment.size(); i++) {
            tags[i - 1] = mListFragment.get(i).getTag();
        }
        outState.putStringArray(ResponseKey.F_TAG, tags);


        //在保存数据之后只需要重启activity  不需要打开其所在的fragment
//        super.onSaveInstanceState(outState);
    }

    private void onEnter(Fragment fragment, String tag) {
        onEnter(fragment, tag, true);
    }
    /**
     * @param fragment
     * @param tag
     * @param isBack   是否支持返回
     */
    private void onEnter(Fragment fragment, String tag, boolean isBack) {
        onEnter(fragment, tag, null, isBack);
    }

    /**
     * @param fragment
     * @param tag
     * @param param    传递的参数
     * @param isBack   是否支持返回
     */
    private void onEnter(Fragment fragment, String tag, Bundle param, boolean isBack) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();

        if (curFragment != null) fTransaction.hide(curFragment);

        if (!fragment.isAdded()) {
            if (param != null) fragment.setArguments(param);

            mListFragment.add(fragment);
            fTransaction.add(R.id.layout_content, fragment, tag);
            if (isBack) fTransaction.addToBackStack(tag);
            fTransaction.show(fragment).commit();
        } else {
            fTransaction.show(fragment).commit();
        }
        curFragment = fragment;
    }


    public void setFunctionsForFragment(String tag) {

        FragmentManager fm = getSupportFragmentManager();
        final BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(tag);

        FunctionsManage functionsManage = FunctionsManage.getInstance();

        /**
         * 导航菜单栏事件
         */
        functionsManage.addFunction(new FunctionWithParamOnly<Integer>(MenuFragment.INTERFACE_WITHP) {
            @Override
            public void function(Integer data) {
                menuId = data;
                if (curFragment instanceof MainFragment) {
                    MainFragment fragment = (MainFragment) curFragment;
                    fragment.onShowFragnentContent(data);
                }
            }
        });

        /**
         * 参数的跳转 需要传入Fragment的ID
         * 数组第一个值必须是FragmentID
         * 第二个之后都是需要传递的参数
         */
        functionsManage.addFunction(new FunctionWithParamOnly<Object[]>(BaseFragment._INTERFACE_WITHP) {
            @Override
            public void function(Object... data) { //第一个参数是id
                AppStatus.setStatusBarLightMode(MainActivity.this, Color.WHITE);
                try {
                    if (data.length > 1) {
                        fragmentId = (int) data[0];
                        Bundle bundle = new Bundle();
                        switch (fragmentId) {
                            case FragmentIDs.F_AddChargeFragment:
                                    onEnter(AddChargeFragment.getInstance(), AddChargeFragment.TAG);
                                break;
                            default:
                                throw new Exception("未知的ID");
                        }
                    } else {
                        throw new Exception("参数不正确");
                    }
                } catch (Exception e) {
                    ToastUtils.onToast(e.getMessage());
                    e.printStackTrace();
                }

            }
        });


        /**
         * 返回按钮的事件监听
         */
        functionsManage.addFunction(new FunctionWithParamOnly<String>(BaseFragment.INTERFACE_BACK) {
            @Override
            public void function(String tag) { //跳转到目标
                if (TextUtils.isEmpty(tag)) {
                    onKeyBack();
                }else {
                    for (int i = mListFragment.size() - 1; i > 0; i--) {
                        Fragment fragment = mListFragment.get(i);

                        if (fragment.getTag().equals(tag)) {
                            return;
                        } else {
                            fragmentManager.popBackStack();
                            mListFragment.remove(mListFragment.size() - 1);
                            curFragment = mListFragment.get(mListFragment.size() - 1);
                        }
                    }
                }
            }
        });

        fragment.setFunctionsManager(functionsManage);
    }


    private void onKeyBack() {

        fragmentManager.popBackStack();
        if (mListFragment.size() > 1) {
            mListFragment.remove(mListFragment.size() - 1);
            curFragment = mListFragment.get(mListFragment.size() - 1);
        } else {
            onExitApp(false, -1);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                onKeyBack();
                return false;
            }
        return super.onKeyDown(keyCode, event);
    }

    /**
     *
     * @param isCache 清除缓存
     * @param type -1 退出应用
     */
    private void onExitApp(final boolean isCache, final int type) {
        String msg = type == -1 ? "您确定退出应用吗？" : "您确定退出当前用户？";
        onShowDialog("提示", msg, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (isCache) {
                    SharedTools.getInstance(MainActivity.this).onClearUserInfo(clearUserInfo);
                }
                dialog.dismiss();
                if (type == -1) {
                    finish();
                } else if (type == 0) {
                    //   onEnter(LoginFragment.getInstance(), LoginFragment.TAG, true);
                }
            }
        }, "取消");
    }



}
