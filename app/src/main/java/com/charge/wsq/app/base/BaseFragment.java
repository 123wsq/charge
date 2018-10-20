package com.charge.wsq.app.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charge.wsq.app.R;
import com.example.wsq.library.struct.FunctionsManage;
import com.example.wsq.library.tools.DividerGridItemDecoration;
import com.example.wsq.library.tools.GlideImageLoader;
import com.example.wsq.library.tools.Permissions.PermissionUtils;
import com.example.wsq.library.tools.RecyclerViewDivider;
import com.example.wsq.library.utils.DensityUtil;
import com.example.wsq.library.utils.ToastUtils;
import com.example.wsq.library.view.alertdialog.CustomDefaultDialog;
import com.example.wsq.library.view.alertdialog.OnDialogClickListener;
import com.example.wsq.library.view.loadding.LoadingDialog;
import com.orhanobut.logger.Logger;
import com.charge.wsq.app.activity.MainActivity;
import com.charge.wsq.app.constant.FragmentIDs;
import com.charge.wsq.app.mvp.presenter.BasePresenter;
import com.charge.wsq.app.mvp.view.BaseView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;


public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment implements BaseView {

    public static final String TAG = BaseFragment.class.getName();
    public static String _INTERFACE_NPNR = TAG + "_NPNR";  //没参数没有返回值
    public static String _INTERFACE_WITHP = TAG + "_WITHP";  //只有参数
    public static String _INTERFACE_WITHR = TAG + "_WITHR";  //只有返回值
    public static String _INTERFACE_WITHPR = TAG + "_WITHPR";  //有参数有返回值
    public static final String FINISH_CURRENT = _INTERFACE_WITHP + TAG;


    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible = false;
    /**
     * Fragment的view是否已创建
     */
    protected boolean mIsViewCreated = false;

    public static String INTERFACE_BACK = "com.charge.wsq.app.base.BaseFragment";
    public static final String INTERFACE_BACK_PAGE = TAG + "BACK_PAGE";
    public FunctionsManage mFunctionsManage;
    public T ipresenter;
    public LoadingDialog dialog;
    private long start_Time;

    //item之间的间隔
    private int mDivicesHeight = 10;
    //item 之间间隔的颜色
    private int mBgColor = R.color.color_left_gray;

    private int mGridLineCount = 2;

    private final int TYPE_LINEAR = 1;
    private final int TYPE_GRID = 2;


    private CustomDefaultDialog defaultDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        start_Time = System.currentTimeMillis();
        View view = inflater.inflate(getLayoutId(), container, false);
        dialog = new LoadingDialog(getContext(), R.drawable.anim_loading_progress);
        ipresenter = createPresenter();
        if (ipresenter != null) {
            ipresenter.attachView((V) this);
        }
        ButterKnife.bind(this, view);
        mIsViewCreated = true;
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }


    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();


    public void setFunctionsManager(FunctionsManage functionsManager) {

        this.mFunctionsManage = functionsManager;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /*
     * onAttach(Context) is not called on pre API 23 versions of Android and onAttach(Activity) is deprecated
     * Use onAttachToContext instead
     */
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            onAttachToContext(context);
        }
    }

    protected void onAttachToContext(Context context) {
        if (context instanceof MainActivity) {
            MainActivity mBaseActivity = (MainActivity) context;

            try {
                if (getTag() != null) {
                    mBaseActivity.setFunctionsForFragment(getTag());
                } else {
                    throw new Exception("TAG 不能为空");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
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
        mFunctionsManage.invokeFunction(_INTERFACE_WITHP, FragmentIDs.F_LoginFragment, "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsViewCreated = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ipresenter != null) {
            ipresenter.deachView();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!mIsViewCreated)//view没有创建的时候不进行操作
            return;

        if (getUserVisibleHint()) {
            if (!isVisible) {//确保在一个可见周期中只调用一次onVisible()
                isVisible = true;
                onVisible();
            }
        } else {
            if (isVisible) {
                isVisible = false;
                onHidden();
            }
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {

    }

    /**
     * fragment不可见的时候操作,onPause的时候,以及不可见的时候调用
     */
    protected void onHidden() {
    }

    @Override
    public void onResume() {//和activity的onResume绑定，Fragment初始化的时候必调用，但切换fragment的hide和visible的时候可能不会调用！
        super.onResume();
        if (isAdded() && !isHidden()) {//用isVisible此时为false，因为mView.getWindowToken为null
            onVisible();
            isVisible = true;
        }
    }

    @Override
    public void onPause() {
        if (isVisible() || isVisible) {
            onHidden();
            isVisible = false;
        }
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {//默认fragment创建的时候是可见的，但是不会调用该方法！切换可见状态的时候会调用，但是调用onResume，onPause的时候却不会调用
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
            isVisible = true;
        } else {
            onHidden();
            isVisible = false;
        }
    }

    public void onInitRecyclerView_L(RecyclerView rv_RecyclerView) {

        onInitRecyclerView_L(rv_RecyclerView, mBgColor);
    }

    public void onInitRecyclerView_L(RecyclerView rv_RecyclerView, int bgColor) {

        onInitRecyclerView(rv_RecyclerView, bgColor, mDivicesHeight, TYPE_LINEAR, 0);
    }

    public void onInitRecyclerView_L(RecyclerView rv_RecyclerView, float divicesHeight) {

        onInitRecyclerView(rv_RecyclerView, mBgColor, divicesHeight, TYPE_LINEAR, 0);
    }

    public void onInitRecyclerView_G(RecyclerView rv_RecyclerView) {

        onInitRecyclerView_G(rv_RecyclerView, mDivicesHeight);
    }

    public void onInitRecyclerView_G(RecyclerView rv_RecyclerView, int gridCount) {

        onInitRecyclerView_G(rv_RecyclerView, mDivicesHeight, gridCount);
    }

    public void onInitRecyclerView_G(RecyclerView rv_RecyclerView, float gridDividerHeight) {

        onInitRecyclerView_G(rv_RecyclerView, gridDividerHeight, mGridLineCount);
    }

    public void onInitRecyclerView_G(RecyclerView rv_RecyclerView, float gridDividerHeight, int gridCount) {

        onInitRecyclerView(rv_RecyclerView, mBgColor, gridDividerHeight, TYPE_GRID, gridCount);
    }

    public void onInitRecyclerView(RecyclerView rv_RecyclerView, int bgColor, float dividerHeight, int type, int gridCount) {


        rv_RecyclerView.setHasFixedSize(true);
        rv_RecyclerView.setNestedScrollingEnabled(false);
        if (type == TYPE_LINEAR) {
            rv_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_RecyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(),
                    LinearLayoutManager.HORIZONTAL, DensityUtil.dp2px(getActivity(), dividerHeight),
                    ContextCompat.getColor(getActivity(), bgColor)));
        } else if (type == TYPE_GRID) {
            rv_RecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), gridCount));
            rv_RecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        }
    }



    public void onInitBanner(Banner banner, final List<String> mImages, List<String> mTitles, final List<String> mLink) {

        //验证传入的参数
        if (banner == null) {
            ToastUtils.onToast("请先初始化轮播图");
            return;
        }
        if (mImages.size() ==0){
            ToastUtils.onToast("请设置轮播图");
            return;
        }
        if (mTitles== null)
            mTitles = new ArrayList<>();
        if (mLink == null){
            ToastUtils.onToast("链接不能为空");
            return;
        }
        if (mTitles.size() ==0 || mLink.size() ==0){
            for (int i = 0; i < mImages.size(); i++) {
                if (mTitles.size() != mImages.size()){
                    mTitles.add("");
                }
                if (mLink.size() !=mImages.size()){
                    mLink.add("");
                }
            }
        }

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);

        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3 * 1000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.DURATION);
        //设置图片集合
        banner.setImages(mImages);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(mTitles);

        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

                if (mLink == null) return;
                String url = mLink.get(position);
                if (!TextUtils.isEmpty(url)) {
//                    mFunctionsManage.invokeFunction(_INTERFACE_WITHP, FragmentIDs.F_WebFragment, url);
                }

//                List<LocalMedia> list = new ArrayList<>();
//                for (int i = 0; i < mImages.size(); i++) {
//
//                    LocalMedia localMedia = new LocalMedia();
//                    localMedia.setPath(mImages.get(i));
//                    list.add(localMedia);
//                }
//                PictureSelector.create(getActivity()).externalPicturePreview( position, list);
            }
        });
    }

    public void onShowDialog(String title, String message) {
        CustomDefaultDialog.Builder builder = new CustomDefaultDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(message)
                .setOkBtn("确定", new OnDialogClickListener() {
                    @Override
                    public void onClick(CustomDefaultDialog dialog, String result, int requestCode) {
                        dialog.dismiss();
                    }
                });
        defaultDialog = builder.create();
        defaultDialog.show();

    }

    public void onShowDialog(String title, String message, String okStr, DialogInterface.OnClickListener listener) {
        CustomDefaultDialog.Builder builder = new CustomDefaultDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(message)
                .setOkBtn(okStr, listener);
        defaultDialog = builder.create();
        defaultDialog.show();

    }

    public void onShowDialog(String title, String message, String okStr, DialogInterface.OnClickListener listener, String cancelStr) {
        CustomDefaultDialog.Builder builder = new CustomDefaultDialog.Builder(getActivity());
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

    public void onShowDialog(String title, String message, String okStr, DialogInterface.OnClickListener listener, String cancelStr, DialogInterface.OnClickListener cancelListener) {
        CustomDefaultDialog.Builder builder = new CustomDefaultDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(message)
                .setOkBtn(okStr, listener)
                .setCancelBtn(cancelStr, cancelListener);

        defaultDialog = builder.create();
        defaultDialog.show();

    }


    /**
     * 动态权限申请
     *
     * @param
     * @param
     */
//    public void onRequestPermission(List<Map<String, Object>> permission, final BaseActivity.OnPermissionListener onPermissionListener) {
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
//        HiPermission.create(getActivity()).permissions(permissions).checkMutiPermission(new PermissionCallback() {
//            @Override
//            public void onClose() {
//                LogUtils.d("用户关闭权限申请");
//                onPermissionListener.onFail();
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
    }

//    public List<Map<String, Object>> onGetCameraPermission() {
//        List<Map<String, Object>> list = new ArrayList<>();
//
//        Map<String, Object> camera = new HashMap<>();
//        camera.put(ResponseKey.permission, Manifest.permission.CAMERA);
//        camera.put(ResponseKey.permission_name, "照相机");
//        camera.put(ResponseKey.permission_icon, R.drawable.permission_ic_camera);
//        list.add(camera);
//        Map<String, Object> photo = new HashMap<>();
//        photo.put(ResponseKey.permission, Manifest.permission.READ_EXTERNAL_STORAGE);
//        photo.put(ResponseKey.permission_name, "相册");
//        photo.put(ResponseKey.permission_icon, R.drawable.permission_ic_storage);
//        list.add(photo);
//
//        return list;
//    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(getActivity(), requestCode, permissions, grantResults, permissionGrant);

    }
    public PermissionUtils.PermissionGrant permissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            Logger.d("权限返回码："+requestCode);
        }
    };
}
