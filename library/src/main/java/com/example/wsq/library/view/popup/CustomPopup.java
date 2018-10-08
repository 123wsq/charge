package com.example.wsq.library.view.popup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.example.wsq.library.R;
import com.example.wsq.library.utils.DensityUtil;

import java.util.List;

public class CustomPopup extends PopupWindow{

    private Activity mContext;
    private View popupView;
    TextView tv_title;
    private List<String> mData;
    private String textColor = "#000000";
    private View.OnClickListener onClickListener;
    private PopupItemListener listener;

    @SuppressLint("WrongConstant")
    public CustomPopup(Activity context, List<String> list, View.OnClickListener clickListener, final PopupItemListener listener)
    {
        this.mContext = context;
        this.mData = list;
        this.onClickListener = clickListener;
        this.listener = listener;
        setSoftInputMode(16);
        this.popupView = LayoutInflater.from(mContext).inflate(R.layout.layout_default_popup, null);
        TextView tv_cancel = (TextView)this.popupView.findViewById(R.id.tv_cancel);

        tv_title = (TextView)this.popupView.findViewById(R.id.tv_title);

        tv_cancel.setOnClickListener(this.onClickListener);
        ListView listview = (ListView)this.popupView.findViewById(R.id.listview);
        MyAdapter adapter = new MyAdapter();
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                listener.onClickItemListener(position, (String)CustomPopup.this.mData.get(position));
            }
        });
        initPopup();
    }

    public void initPopup()
    {
        int w = this.mContext.getResources().getDisplayMetrics().widthPixels;
        int h = this.mContext.getResources().getDisplayMetrics().heightPixels;

        setContentView(this.popupView);

        setWidth((int)(w * 0.9D));

        int height = 0;
        if (null != this.mData)
        {
            if (h / 2 <= DensityUtil.dp2px(this.mContext, this.mData.size() * 50 + 125)) {
                height = h / 2;
            } else {
                height = DensityUtil.dp2px(this.mContext, this.mData.size() * 50 + 125);
            }
        }
        else {
            height = h / 2;
        }
        setHeight(height);

        setFocusable(true);

        setAnimationStyle(R.style.style_pop);

        ColorDrawable dw = new ColorDrawable(0);

        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);

        setInputMethodMode(1);

        this.popupView.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View v, MotionEvent event)
            {
                return false;
            }
        });
    }

    public void onTitle(String title){
        tv_title.setText(title);
    }

    public void onSetHeight(int height){
        setHeight(DensityUtil.dp2px(mContext, height));
    }
    public void showAtLocation(View parent, int gravity, int x, int y)
    {
        super.showAtLocation(parent, gravity, x, y);
        backgroundAlpha(0.5F);
    }



    public void dismiss()
    {
        super.dismiss();
        backgroundAlpha(1.0F);
    }

    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = this.mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        this.mContext.getWindow().setAttributes(lp);
    }

    public static abstract interface PopupItemListener
    {
        public abstract void onClickItemListener(int paramInt, String paramString);
    }

    class MyAdapter
            extends BaseAdapter
    {
        MyAdapter() {}

        public int getCount()
        {
            return CustomPopup.this.mData.size();
        }

        public Object getItem(int position)
        {
            return CustomPopup.this.mData.get(position);
        }

        public long getItemId(int position)
        {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(CustomPopup.this.mContext).inflate(R.layout.layout_default_popup_item, null);
                holder = new ViewHolder();
                convertView.setTag(holder);

                holder.tv_popup_name = ((TextView)convertView.findViewById(R.id.tv_popup_name));
            }
            else
            {
                holder = (ViewHolder)convertView.getTag();
            }
            if (!TextUtils.isEmpty(textColor)) {
                holder.tv_popup_name.setTextColor(Color.parseColor(textColor));
            }
            holder.tv_popup_name.setText(CustomPopup.this.mData.get(position) + "");
            return convertView;
        }

        class ViewHolder
        {
            TextView tv_popup_name;

            ViewHolder() {}
        }
    }

    public void setTextColor(String color)
    {
        this.textColor = color;
    }
}
