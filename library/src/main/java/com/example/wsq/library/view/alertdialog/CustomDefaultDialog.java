package com.example.wsq.library.view.alertdialog;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wsq.library.R;
import com.example.wsq.library.utils.DensityUtil;


/**
 * Created by wsq on 2016/5/6.
 */
public class CustomDefaultDialog extends Dialog {

    public CustomDefaultDialog(Context context) {
        super(context);
    }

    public CustomDefaultDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context; //上下文对象
        private String title; //对话框标题
        /*按钮坚挺事件*/
        private OnClickListener okListener, cancelListener;
        private OnDialogClickListener okListenerInput;



        private String ok, cancel, message;
        private boolean isInput = false;   //是否显示输入框
        private String mTitleColor = "#000000";
        private String mMsgColor = mTitleColor;
        private String mOkBtnFontColor = "#555555";
        private String mCancelBtnFontColor = "#555555";
        private String mOkBtnbackgroundColor = "#FFFFFF";
        private String mCancelBtnbackgroundColor = "#FFFFFF";

        private String mHintColor = "#A9A9A9";
        private String mHintMsg;
        private String mInputMsg;
        private boolean isCloseDiaglog = false;    //是否显示右上角关闭按钮
        private boolean isShowMessage = true;  //表示是否显示消息内容
        private int requestCode = -1;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setIsShowInput(boolean isInput) {
            this.isInput = isInput;
            return this;
        }

        public Builder setTitleColor(String titleColor) {
            this.mTitleColor = titleColor;
            return this;
        }

        public Builder setShowCloseDialog(boolean isShow) {
            this.isCloseDiaglog = isShow;
            return this;
        }

        public Builder setShowMessage(boolean isShow) {
            this.isShowMessage = isShow;
            return this;
        }

        public Builder setMessageColor(String msgColor) {
            this.mMsgColor = msgColor;
            return this;
        }
        public Builder setOkBtnColor(String okbtnColor) {
            this.mOkBtnFontColor = okbtnColor;
            return this;
        }
        public Builder setCancelBtnColor(String cancelbtnColor) {
            this.mCancelBtnFontColor = cancelbtnColor;
            return this;
        }
        public Builder setOkBtnBackgroundColor(String okbtnbackgroundColor) {
            this.mOkBtnbackgroundColor = okbtnbackgroundColor;
            return this;
        }
        public Builder setCancelBtnbackgroundColor(String cancelbtnbackgroundColor) {
            this.mCancelBtnbackgroundColor = cancelbtnbackgroundColor;
            return this;
        }

        public Builder setInputHintColor(String hintColor){
            this.mHintColor = hintColor;
            return this;
        }

        public Builder setHintMsg(String hintMsg){
            this.mHintMsg = hintMsg;
            return this;
        }
        public Builder setInputMsg(String inputMsg){
            this.mInputMsg = inputMsg;
            return this;
        }
        public Builder setRequestCode(int code) {
            this.requestCode = code;
            return this;
        }


        public Builder setOkBtn(String okStr, OnDialogClickListener listener) {
            this.ok = okStr;
            this.okListenerInput = listener;
            return this;
        }

        public Builder setOkBtn(String okStr, OnClickListener listener) {
            this.ok = okStr;
            this.okListener = listener;
            return this;
        }

        public Builder setCancelBtn(String cancelStr, OnClickListener listener) {
            this.cancel = cancelStr;
            this.cancelListener = listener;
            return this;
        }

        public CustomDefaultDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomDefaultDialog dialog = new CustomDefaultDialog(context, R.style.mystyle);
            View layout = inflater.inflate(R.layout.layout_default_dialog, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            //设置标题栏
            TextView tv_title = (TextView) layout.findViewById(R.id.title);


            //右上角关闭按钮
            ImageView iv_close = layout.findViewById(R.id.iv_close);


            //显示的内容
            TextView dialog_message = (TextView) layout.findViewById(R.id.dialog_message);


            //显示输入框
            final EditText tv_inputMessage = layout.findViewById(R.id.tv_inputMessage);



            //按钮点击显示
//            LinearLayout dialog_btn_layout = (LinearLayout) layout.findViewById(R.id.dialog_btn_layout);

            //按钮中间的线
            View dialig_view = layout.findViewById(R.id.dialig_view);
            // set the content message
//            dialog_btn_layout.setVisibility(View.VISIBLE);



            Button dialog_ok = (Button) layout.findViewById(R.id.dialog_ok);
            Button dialog_cancel = (Button) layout.findViewById(R.id.dialog_cancel);


            /**
             * 设置字体颜色
             *
             */
            tv_title.setText(title);
            tv_title.setTextColor(Color.parseColor(mTitleColor));
            tv_title.getPaint().setFakeBoldText(true);
            tv_title.setTextColor(Color.parseColor(mTitleColor));

            dialog_message.setTextColor(Color.parseColor(mMsgColor));
            dialog_message.setText(message);
            dialog_message.setVisibility(isShowMessage ? View.VISIBLE : View.GONE);

            tv_inputMessage.setTextColor(Color.parseColor(mMsgColor));
            tv_inputMessage.setHintTextColor(Color.parseColor(mHintColor));
            tv_inputMessage.setText(mInputMsg);
            tv_inputMessage.setVisibility(isInput ? View.VISIBLE : View.GONE);
            tv_inputMessage.setHint(TextUtils.isEmpty(mHintMsg) ? "请输入" : mHintMsg);

            dialog_ok.setText(ok +"");
            dialog_ok.setTextColor(Color.parseColor(mOkBtnFontColor));
//            dialog_ok.setBackgroundColor(Color.parseColor(mOkBtnbackgroundColor));

            dialog_cancel.setText(cancel +"");
            dialog_cancel.setTextColor(Color.parseColor(mCancelBtnFontColor));
//            dialog_cancel.setBackgroundColor(Color.parseColor(mCancelBtnbackgroundColor));

            iv_close.setVisibility(isCloseDiaglog ? View.VISIBLE : View.GONE);



            //关闭按钮事件
            if (isCloseDiaglog){
                iv_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }

            /**
             *
             *确认按钮事件
             */
            if (okListener != null){
                dialog_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        okListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                    }
                });
            }
            //带有输入框的dialog事件
            if (okListenerInput !=null){
                dialog_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        okListenerInput.onClick(dialog, tv_inputMessage.getText().toString() + "", requestCode);
                    }
                });
            }

            //取消按钮
            if (cancelListener !=null){
                dialog_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cancelListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                    }
                });
            }

            //判断显示按钮  1.当两个按钮都存在的情况下    并且不需要返回值的时候
            if (okListener!=null && cancelListener!= null){
                dialog_ok.setBackgroundDrawable(getBackgroundDrawable(mOkBtnbackgroundColor, DensityUtil.dp2px(context, 15), 0));
                dialog_cancel.setBackgroundDrawable(getBackgroundDrawable(mCancelBtnbackgroundColor, 0, DensityUtil.dp2px(context, 15)));
                dialog_cancel.setVisibility(View.VISIBLE);
                dialog_ok.setVisibility(View.VISIBLE);
                dialig_view.setVisibility(View.VISIBLE);
            }
            // 2 当两个按钮都存在  并且需要返回值的情况
            if (okListenerInput !=null && cancelListener!= null){

                dialog_ok.setBackgroundDrawable(getBackgroundDrawable(mOkBtnbackgroundColor, DensityUtil.dp2px(context, 15), 0));
                dialog_cancel.setBackgroundDrawable(getBackgroundDrawable(mCancelBtnbackgroundColor, 0, DensityUtil.dp2px(context, 15)));
                dialog_cancel.setVisibility(View.VISIBLE);
                dialog_ok.setVisibility(View.VISIBLE);
                dialig_view.setVisibility(View.VISIBLE);
            }


            /**
             * 只需要一个按钮的情况
             * 1 只需要确定按钮
             */

             if ((okListener != null || okListenerInput != null) &&  cancelListener == null) {
                 dialog_ok.setBackgroundDrawable(getBackgroundDrawable(mOkBtnbackgroundColor, DensityUtil.dp2px(context, 15), DensityUtil.dp2px(context, 15)));
                dialog_ok.setVisibility(View.VISIBLE);
                dialog_cancel.setVisibility(View.GONE);
                dialig_view.setVisibility(View.GONE);
             }
            //只需要取消按钮
            if (okListener == null && okListenerInput == null &&  cancelListener != null) {
                dialog_cancel.setBackgroundDrawable(getBackgroundDrawable(mCancelBtnbackgroundColor, DensityUtil.dp2px(context, 15), DensityUtil.dp2px(context, 15)));
                dialog_cancel.setVisibility(View.VISIBLE);
                dialog_ok.setVisibility(View.GONE);
                dialig_view.setVisibility(View.GONE);
            }



            Window dialogWindow = dialog.getWindow();
            float widthPixels = context.getResources().getDisplayMetrics().widthPixels;
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            // p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
            p.width = (int) (widthPixels * 0.75); // 宽度设置为屏幕的0.65
            dialogWindow.setAttributes(p);
            //setCanceledOnTouchOutside(false);调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
//            dialog.setCanceledOnTouchOutside(true);
            dialog.setContentView(layout);
            //setCancelable(false);调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
            dialog.setCancelable(false);
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
