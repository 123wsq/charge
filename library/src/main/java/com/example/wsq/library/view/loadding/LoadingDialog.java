package com.example.wsq.library.view.loadding;


import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Window;

import com.example.wsq.library.R;


public class LoadingDialog extends Dialog {
	private String str="加载中...";
	SysLoading loadingView;
	private int animationId;


	public LoadingDialog(Context context) {
		super(context, R.style.FullScreenDialogAct);
		init(context);
	}

	public LoadingDialog(Context context, int resId) {
		super(context, R.style.FullScreenDialogAct);
		this.animationId = resId;
		init(context);
	}
	private void init(Context context){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		loadingView  = new SysLoading(context);

		loadingView.setAnimationId(animationId);
		setContentView(loadingView);

	}

	@Override
	public void show() {
		super.show();
		if (loadingView!= null){
			loadingView.showAnim();
		}
	}

	@Override
	public void dismiss() {
		super.dismiss();
		if (loadingView!= null){
			loadingView.stopAnim();
		}
	}

	public void setText(String str){
		if (!TextUtils.isEmpty(str)){
			loadingView.setText(str);
		}
	}

}

