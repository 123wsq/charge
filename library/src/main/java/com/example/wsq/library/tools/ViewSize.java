package com.example.wsq.library.tools;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.wsq.library.utils.DensityUtil;


public class ViewSize {

    /**
     * 设置raidoButton的图片大小
     * @param context
     * @param radioButton
     * @param drawableId
     */
    public static void onSetRadioButtonSize(Context context, RadioButton radioButton, int drawableId){

        Drawable drawableFirst = context.getResources().getDrawable(drawableId);
        drawableFirst.setBounds(0,  0, DensityUtil.dp2px(context, 20),
                DensityUtil.dp2px(context, 20));//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioButton.setCompoundDrawables(null, drawableFirst, null, null);
    }

    /**
     * 设置raidoButton的图片大小
     * @param context
     * @param radioButton
     * @param drawableId
     */
    public static void onSetRadioButtonSize(Context context, RadioButton radioButton, int drawableId, int imageSize){

        Drawable drawableFirst = context.getResources().getDrawable(drawableId);
        drawableFirst.setBounds(0,  0, DensityUtil.dp2px(context, imageSize),
                DensityUtil.dp2px(context, imageSize));//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioButton.setCompoundDrawables(drawableFirst, null, null, null);
    }


    /**
     * 设置raidoButton的图片大小
     * @param context
     * @param radioButton
     * @param drawableId
     */
    public static void onSetRadioButtonTopSize(Context context, RadioButton radioButton, int drawableId, int imageSize){

        Drawable drawableFirst = context.getResources().getDrawable(drawableId);
        drawableFirst.setBounds(0,  0, DensityUtil.dp2px(context, imageSize),
                DensityUtil.dp2px(context, imageSize));//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        radioButton.setCompoundDrawables(null, drawableFirst, null, null);
    }



    public static void onSetCheckBoxImageSize(Context context, CheckBox checkBox, int drawableId){

        Drawable drawableFirst = context.getResources().getDrawable(drawableId);
        drawableFirst.setBounds(0,  0, DensityUtil.dp2px(context, 20),
                DensityUtil.dp2px(context, 20));//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        checkBox.setCompoundDrawables(drawableFirst, null, null, null);
        //创建ImageSpan对象
        ImageSpan imageSpan=new ImageSpan(drawableFirst);
        //创建SpannableStringBuilder对象
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder("特别关注");
        //将imageSpan放入spannableStringBuilder中
        spannableStringBuilder.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置cb的文本,将spannableStringBuilder放入
//        checkBox.setText(spannableStringBuilder);
        checkBox.setText("特别关注");
    }

    public static void onSetCheckBoxImageSize(Context context, CheckBox checkBox, int drawableId, int imageSize){

        Drawable drawableFirst = context.getResources().getDrawable(drawableId);
        drawableFirst.setBounds(0,  0, DensityUtil.dp2px(context, 20),
                DensityUtil.dp2px(context, 20));//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度
        checkBox.setCompoundDrawables(drawableFirst, null, null, null);
        //创建ImageSpan对象
//        ImageSpan imageSpan=new ImageSpan(drawableFirst);
        //创建SpannableStringBuilder对象
//        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder("特别关注");
        //将imageSpan放入spannableStringBuilder中
//        spannableStringBuilder.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        //设置cb的文本,将spannableStringBuilder放入
//        checkBox.setText(spannableStringBuilder);
//        checkBox.setText("特别关注");
    }
}
