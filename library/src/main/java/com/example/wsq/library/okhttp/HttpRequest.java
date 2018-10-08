package com.example.wsq.library.okhttp;



import com.example.wsq.library.okhttp.callback.OnMvpCallBack;
import com.example.wsq.library.utils.LogUtils;
import com.example.wsq.library.utils.StringToMap;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.Call;


public class HttpRequest {

    private String url;
    private Map<String, String> param;
    private OnMvpCallBack<Map<String, Object>> callBack;

    public  void onSendGetRequest(final String url, Map<String, String> param, final boolean isIntercept, final OnMvpCallBack<Map<String, Object>> callBack)throws  Exception{
        this.url = url;
        this.param = param;
        this.callBack = callBack;

        LogUtils.d("请求 URL:"+url +"\t param:  "+ param.toString());
        OkhttpUtil.okHttpGet(url, param, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

                callBack.onFailure("请求失败");
                callBack.onComplete();
            }

            @Override
            public void onResponse(String s) {


                try {
                    LogUtils.d("请求返回 ： \nurl:"+url+"\n返回值："+s);
                    Map<String, Object> result = StringToMap.onString2Map(s);
                    if (isIntercept){
                        callBack.onSuccess(result);
                    }else{
                        onResponseValidate(result, callBack);
                    }
                } catch (Exception e) {
                    callBack.onFailure("错误的数据格式");
                    e.printStackTrace();
                }
                callBack.onComplete();
            }
        });
    }


    public  void onSendPostRequest(final String url, Map<String, String> param, final boolean isIntercept, final OnMvpCallBack<Map<String, Object>> callBack) throws Exception{

        LogUtils.d("请求 URL:"+url +" \t param:  "+ param.toString());
        OkhttpUtil.okHttpPost(url, param, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

                callBack.onFailure("请求失败");
                callBack.onComplete();
            }

            @Override
            public void onResponse(String s) {

                try {
                    LogUtils.d("请求返回 ： url:"+url+"\t\t返回值："+s);

                    Map<String, Object> result = StringToMap.onString2Map(s);
                    if (isIntercept){
                        callBack.onSuccess(result);
                    }else {
                        onResponseValidate(result, callBack);
                    }
                } catch (Exception e) {
                    callBack.onFailure("错误的数据格式");
                    e.printStackTrace();
                }
                callBack.onComplete();
            }
        });
    }




    /**
     * 上传单个文件
     * @param url
     * @param file
     * @param fileKey
     * @param fileType
     * @param param
     * @param callBack
     */
    public void onUploadFile(String url, File file, String fileKey, String fileType, Map<String, String> param, final OnMvpCallBack<Map<String, Object>> callBack){

        LogUtils.d("请求地址:" + url+", fileKey:"+ fileKey +", fileType: "+fileType);
        OkhttpUtil.okHttpUploadFile(url,file, fileKey, fileType, param, new CallBackUtil.CallBackString(){
            @Override
            public void onFailure(Call call, Exception e) {
                callBack.onFailure("请求失败");
                callBack.onComplete();
            }
            @Override
            public void onResponse(String response) {

                LogUtils.d("Response  "+response);
                try {
                    Map<String, Object> result = StringToMap.onString2Map(response);

                    onResponseValidate(result, callBack);
                } catch (Exception e) {
                    callBack.onFailure("错误的数据格式");
                    e.printStackTrace();
                }
                callBack.onComplete();

            }

        });
    }

    /**
     * 上传多个文件
     * @param url
     * @param files
     * @param param
     * @param callBack
     */
    public void onUploadFiles(String url, List<Map<String, Object>> files, Map<String, String> param, final OnMvpCallBack<Map<String, Object>> callBack){

        LogUtils.d("请求地址:" + url+", files:"+ files.toString() +", param: "+ param.toString());
        OkhttpUtil.okHttpUploadFiles(url,files, param, new CallBackUtil.CallBackString(){
            @Override
            public void onFailure(Call call, Exception e) {
                callBack.onFailure("请求失败");
                callBack.onComplete();
            }
            @Override
            public void onResponse(String response) {

                LogUtils.d("Response  "+response);
                try {
                    Map<String, Object> result = StringToMap.onString2Map(response);

                    onResponseValidate(result, callBack);
                } catch (Exception e) {
                    callBack.onFailure("错误的数据格式");
                    e.printStackTrace();
                }
                callBack.onComplete();

            }

        });
    }

    private void onResponseValidate(Map<String, Object> data, final OnMvpCallBack<Map<String, Object>> callBack){

        int code = Integer.parseInt(data.get("code")+"");
        String msg = data.get("message")+"";
        switch (code){
            case 100:
                callBack.onSuccess(data);
                break;
            case 107:
                callBack.onOutTime(msg);
                break;
            default:
                callBack.onFailure(msg);
                break;
        }
    }
}
