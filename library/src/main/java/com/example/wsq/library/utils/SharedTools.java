package com.example.wsq.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedTools {


    private final String SHARED_NAME = "shoumai";
    private static SharedTools sharedTools;

    private Context mContext;

    private SharedPreferences shared;

    public SharedTools(Context context) {
        this.mContext = context;
        shared = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
    }


    public static SharedTools getInstance(Context context) {

        if (sharedTools == null) {
            synchronized (SharedTools.class) {
                if (sharedTools == null) {
                    sharedTools = new SharedTools(context);
                }

            }

        }

        return sharedTools;
    }

    /**
     * 数据保存
     *
     * @param key
     * @param data
     */
    public void onPutData(String key, Object data) {

        SharedPreferences.Editor editor = shared.edit();

        if (data instanceof String) {

            editor.putString(key, (String) data);

        } else if (data instanceof Boolean) {

            editor.putBoolean(key, (Boolean) data);

        } else if (data instanceof Float) {

            editor.putFloat(key, (Float) data);

        } else if (data instanceof Integer) {

            editor.putInt(key, (Integer) data);

        } else if (data instanceof Long) {

            editor.putLong(key, (Long) data);

        } else {

            try {

                throw new Exception("未知数据类型" + key +"====="+ data);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        editor.commit();

    }


    public String onGetString(String key) {

        return shared.getString(key, "");

    }

    public boolean onGetBoolean(String key) {

        return shared.getBoolean(key, false);

    }

    public float onGetFloat(String key) {

        return shared.getFloat(key, -1);

    }

    public int onGetInt(String key) {

        return shared.getInt(key, -1);

    }

    public long onGetLong(String key) {
        return shared.getLong(key, -1);
    }


    public void onClearUserInfo(String... keys) {
        for (int i = 0; i < keys.length; i++) {
            shared.edit().putString(keys[i], "").commit();
        }
    }

    public void onClearWXType() {

        shared.edit().putString("openVipType", "").commit();
    }
}
