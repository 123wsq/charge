package com.example.wsq.library.utils;

import android.text.TextUtils;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StringToMap {



    public static Map<String, Object> onString2Map(String str) {

        Map<String, Object> data = new HashMap<String, Object>();

        try {

            JSONObject jsonObject = new JSONObject(str);

            Iterator<String> iterator = jsonObject.keys();

            while (iterator.hasNext()) {
                String key = iterator.next();
                Object object = jsonObject.get(key);

                if (object instanceof JSONObject) {
                    Map<String, Object> map = onString2Map(object.toString());
                    data.put(key, map);
                }else if(object instanceof JSONArray){

                        JSONArray jsonArray = (JSONArray) object;
                    List list = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (jsonArray.get(i) instanceof JSONObject) {
                            list.add(onString2Map(jsonArray.get(i).toString()));
                        }else {
                            list.add(jsonArray.get(i));
                        }

                    }
                    data.put(key, list);

                }else if(object instanceof String){
                    data.put(key, (object+"").trim());
                }else {

                    if (TextUtils.isEmpty(object+"") || object.toString().equals("null") || object == null){
                        data.put(key, "");
                    }else {
                        data.put(key, object);
                    }
                }

            }

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return data;
    }
}
