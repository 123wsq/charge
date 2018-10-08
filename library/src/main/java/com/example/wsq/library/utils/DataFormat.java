package com.example.wsq.library.utils;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wsq on 2018/1/4.
 */

public class DataFormat {


    /**
     * 拼接Intent的参数
     * @param intent
     * @param param
     * @param object
     * @return
     */
    public static Intent getIntentData(Intent intent, Map<String, Object> param,
                                Object object){
        // TODO Auto-generated method stub
        try {
            Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();

            while (it.hasNext()) {

                Map.Entry<String, Object> entry = it.next();

                Object obj = entry.getValue();
                String key = entry.getKey();

                if (obj instanceof Boolean) {

                    intent.putExtra(key, (Boolean) obj);

                } else if (obj instanceof Float) {

                    intent.putExtra(key, (Float) obj);

                } else if (obj instanceof Double) {

                    intent.putExtra(key, (Double) obj);

                } else if (obj instanceof Integer) {

                    intent.putExtra(key, (Integer) obj);

                } else if (obj instanceof Long) {

                    intent.putExtra(key, (Long) obj);

                } else if (obj instanceof String) {

                    intent.putExtra(key, obj.toString());
                }

            }

            if (object != null) {

//                Bundle bundle = new Bundle();
//                bundle.putSerializable(IntentParam.INTENT_OBJ,
//                        (Serializable) object);
//                intent.putExtras(bundle);
            }
        } catch (Exception e) {
            LogUtils.d("设置Intent参数异常");
            e.printStackTrace();
        }
        return intent;
    }

    /**
     *
     * @param bundle
     * @param param
     * @param object
     * @return
     */
    public static  Bundle getBundleData(Bundle bundle, Map<String, Object> param,
                                Object object){
        // TODO Auto-generated method stub

        try {

            Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();

            while (it.hasNext()) {

                Map.Entry<String, Object> entry = it.next();

                Object obj = entry.getValue();
                String key = entry.getKey();

                if (obj instanceof Boolean) {

                    bundle.putBoolean(key, (Boolean) obj);

                } else if (obj instanceof Float) {

                    bundle.putFloat(key, (Float) obj);

                } else if (obj instanceof Double) {

                    bundle.putDouble(key, (Double) obj);

                } else if (obj instanceof Integer) {

                    bundle.putInt(key, (Integer) obj);

                } else if (obj instanceof Long) {

                    bundle.putLong(key, (Long) obj);

                } else if (obj instanceof String) {

                    bundle.putString(key, obj.toString());
                }

            }

            if (object != null) {
//                bundle.putSerializable(IntentParam.INTENT_OBJ,
//                        (Serializable) object);
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.d("设置Bundle 数据异常");
            e.printStackTrace();
        }

        return bundle;
    }



    /**
     *String转换成Int
     * @param str
     * @return
     */
    public static int getInt(String str) {
        // TODO Auto-generated method stub

        int i = 0;
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(str);
        try {
            if (m.find()) {
                i = Integer.valueOf(m.group());
            } else {
                LogUtils.d("亲输入正确的数字");
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            LogUtils.d("转换数字失败");
            e.printStackTrace();
        }
        return i;
    }

    /**
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static float getFloat(String str) throws Exception {
        // TODO Auto-generated method stub

        Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
        Matcher m = p.matcher(str);
        float i = 0;

        try {
            if (m.find()) {
                i = Float.valueOf(m.group());
            }
        } catch (Exception e) {
            LogUtils.d("转换float失败");
            // TODO: handle exception
        }

        return i;
    }

    /**
     * 设置隐藏字符串
     * @param str   需要隐藏的字符串
     * @param start   开始位置
     * @param charCount   特殊字符个数
     * @param end   结束位置
     * @param c   特殊字符
     * @param isSpec
     * @return
     */
    public static String dataHidden(String str, int start, int charCount, int end, char c, boolean isSpec)
            {
        // TODO Auto-generated method stub

        if (TextUtils.isEmpty(str)){
            LogUtils.d("字符串不能为空");
            return "";
        }

                LogUtils.d("验证字符串的长度是否大于  start and end");
        if (str.length() < start || str.length() < end) {
            LogUtils.d("显示的明文长度不能大于字符串本身长度");
        }
        StringBuffer sb = new StringBuffer();
        try{
            sb.append(str.substring(0, start));
            LogUtils.d("开始拼接隐藏部分");
            if (isSpec) {
                if (str.length() > start+end) {
                    for (int i = 0; i < str.length()- start-end ; i++) {
                        sb.append(c);
                    }
                }
            }else{
                for (int i = 0; i < charCount; i++) {
                    sb.append(c);
                }
            }
            LogUtils.d("开始拼接隐藏的后面显示部分");
            sb.append(str.substring(str.length()-end, str.length()));

        }catch(Exception e){
            LogUtils.d("字符串截取异常");
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 数据分割
     * @param str
     * @param arrays  分割后的字符串每个的长度，注意此处写的长度之后要和字符串的总长度一致
     * @return
     */

    public static String dataSpec(String str, int[] arrays){
        StringBuffer sb = new StringBuffer();

        try {
            int total = 0;
            for (int i = 0; i < arrays.length; i++) {
                total +=arrays[i];
            }
            System.out.println("length: "+ total);
            if (str.length() != total) {
                LogUtils.d("字符长度和分割长度不一致");
               return "";
            }

            int num= 0;
            for (int i = 0; i < arrays.length; i++) {

                sb.append(str.substring(num, num+arrays[i]));
                sb.append(" ");
                num += arrays[i];
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            LogUtils.d("数据分隔出错");
            e.printStackTrace();

        }
        return sb.toString();
    }

    /**
     *
     * @param str
     * @param spec  分割字符串的长度
     * @return
     */
    public static String dataSpec(String str, int spec) {

        int strLenght = str.length();

        if (strLenght<spec) {
            LogUtils.d("字符长度不能小于分隔长");
            return "";
        }

        int num[] = new int[]{};
        try {
            int size = strLenght / spec;
            int end = strLenght % spec;
            num = new int[size+(end==0 ? 0 :1 )];
            for (int i = 0; i < size; i++) {
                num[i] = spec;
            }

            if (end != 0) {
                num[size] = end;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LogUtils.d("数据分隔出错");
        }
        return dataSpec(str, num);
    }


    /**
     * 将字符串转换成数字
     * @param str
     * @return
     */
    public static int onStringForInteger(String str){

        if (TextUtils.isEmpty(str)){
            return -1;
        }

        if (!isNumber(str)){

            return -1;
        }
        double d = Double.parseDouble(str);
        return (int)d;
    }

    /**
     * 检测是否是数字
     *
     * 验证正数   [0-9]*
     * 验证负数   -?[0-9]+
     * 所有数字   -?[0-9]+.?[0-9]+
     * @return
     */
    public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("^[+-]?(([0-9]\\\\d*))(\\\\.\\\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;

    }

}
