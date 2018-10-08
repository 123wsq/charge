package com.charge.wsq.app.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.wsq.library.view.alertdialog.CustomDefaultDialog;
import com.example.wsq.library.view.loadding.LoadingDialog;
import com.charge.wsq.app.mvp.presenter.BasePresenter;
import com.charge.wsq.app.mvp.view.BaseView;
import com.example.wsq.library.tools.status.AppStatus;
import com.umeng.analytics.MobclickAgent;
import butterknife.ButterKnife;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements BaseView {

    protected T ipresenter;
    private LoadingDialog dialog;
    private CustomDefaultDialog defaultDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);


        setContentView(getLayoutId());
        AppStatus.setStatusBarLightMode(this, Color.WHITE);
        ipresenter = createPresenter();
        if (ipresenter != null) {
            ipresenter.attachView((V) this);
        }
        dialog = new LoadingDialog(this);
        ButterKnife.bind(this);

        initView(savedInstanceState);
    }

    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ipresenter != null)
            ipresenter.deachView();
    }

    @Override
    public void showLoadding() {
        if (!dialog.isShowing()) dialog.show();
    }

    @Override
    public void dismissLoadding() {
        if (dialog.isShowing()) dialog.dismiss();
    }

    @Override
    public void loadFail(String errorMsg) {

    }

    @Override
    public void onReLogin() {

    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void onShowDialog(String title, String message, String okStr, DialogInterface.OnClickListener listener) {
        CustomDefaultDialog.Builder builder = new CustomDefaultDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setOkBtn(okStr, listener);
        defaultDialog = builder.create();
        defaultDialog.show();

    }

    public void onShowDialog(String title, String message, String okStr, DialogInterface.OnClickListener listener, String cancelStr) {
        CustomDefaultDialog.Builder builder = new CustomDefaultDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setOkBtn(okStr, listener)
                .setCancelBtn(cancelStr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        defaultDialog = builder.create();
        defaultDialog.show();

    }

    public void onShowDialog(String title, String message, String okStr, DialogInterface.OnClickListener listener, String cancelStr, DialogInterface.OnClickListener cancelClickListener) {
        CustomDefaultDialog.Builder builder = new CustomDefaultDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setOkBtn(okStr, listener)
                .setCancelBtn(cancelStr, cancelClickListener);

        defaultDialog = builder.create();
        defaultDialog.show();

    }


    /**
     * 动态权限申请
     *
     * @param
     * @param
     */
//    public void onRequestPermission(List<Map<String, Object>> permission, final OnPermissionListener onPermissionListener) {
//
//
//        List<PermissionItem> permissions = new ArrayList<PermissionItem>();
//
//        for (int i = 0; i < permission.size(); i++) {
//            Map<String, Object> map = permission.get(i);
//            permissions.add(new PermissionItem(map.get(ResponseKey.permission) + "",
//                    map.get(ResponseKey.permission_name) + "", Integer.parseInt(map.get(ResponseKey.permission_icon) + "")));
//        }
//
//        HiPermission.create(this).permissions(permissions).checkMutiPermission(new PermissionCallback() {
//            @Override
//            public void onClose() {
//                LogUtils.d("用户关闭权限申请");
//            }
//
//            @Override
//            public void onFinish() {
//                LogUtils.d("所有权限申请完成");
//                onPermissionListener.onFinish();
//            }
//
//            @Override
//
//            public void onDeny(String permission, int position) {
//
//            }
//
//            @Override
//            public void onGuarantee(String permission, int position) {
//
//            }
//        });
//    }

    public interface OnPermissionListener {
        void onFinish();

        void onFail();
    }
}
