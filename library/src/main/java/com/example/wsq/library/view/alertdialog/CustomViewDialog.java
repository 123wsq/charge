package com.example.wsq.library.view.alertdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wsq.library.R;


/**
 * Created by wsq on 2016/5/6.
 */
public class CustomViewDialog extends Dialog {

    public CustomViewDialog(Context context) {
        super(context);
    }

    public CustomViewDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context; //上下文对象
        /*按钮坚挺事件*/
        private OnClickListener okListener, cancelListener;
        private OnDialogClickListener okListenerInput;

        private View dialogView;
        private float width = 0.75f;  //dialog显示的宽度  width < 1
        private float height = 0;    //dialog显示的高度，是以宽度计算比例
        private boolean isback = true;  //设置点击返回键和dialog是否返回  默认是返回


        public Builder(Context context) {
            this.context = context;
        }

        public Builder setView(View view) {
            this.dialogView = view;
            return this;
        }
        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }
        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }
        public Builder setBack(boolean isback) {
            this.isback = isback;
            return this;
        }



        public CustomViewDialog create() {

            // instantiate the dialog with the custom Theme
            final CustomViewDialog dialog = new CustomViewDialog(context, R.style.mystyle);
            dialog.addContentView(dialogView, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            Window dialogWindow = dialog.getWindow();
            float widthPixels = context.getResources().getDisplayMetrics().widthPixels;
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            if (height>0){
                 p.height = (int) (widthPixels * height); // 高度设置为屏幕的0.6
            }

            p.width = (int) (widthPixels * width); // 宽度设置为屏幕的0.65
            dialogWindow.setAttributes(p);
            //setCanceledOnTouchOutside(false);调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
//            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(dialogView);
            //setCancelable(false);调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
            dialog.setCancelable(isback);
            return dialog;
        }

    }

    /**
     * 产生shape类型的drawable
     *
     * @param solidColor
     * @return
     */
    public static GradientDrawable getBackgroundDrawable(String solidColor, float leftDownRadius, float rightDownRadius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor(solidColor));
//        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setCornerRadii(new float[]{0, 0, 0, 0, rightDownRadius, rightDownRadius, leftDownRadius, leftDownRadius});
        return drawable;
    }

}
