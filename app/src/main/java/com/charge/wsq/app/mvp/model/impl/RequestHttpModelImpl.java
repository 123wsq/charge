package com.charge.wsq.app.mvp.model.impl;


import com.example.wsq.library.okhttp.HttpRequest;
import com.example.wsq.library.okhttp.callback.OnMvpCallBack;
import com.example.wsq.library.utils.SharedTools;
import com.example.wsq.library.utils.ToastUtils;
import com.charge.wsq.app.activity.MainActivity;
import com.charge.wsq.app.mvp.callback.OnResponseCallBack;
import com.charge.wsq.app.mvp.model.inter.RequestHttpModel;

import com.charge.wsq.app.mvp.view.BaseView;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.List;
import java.util.Map;


public class RequestHttpModelImpl implements RequestHttpModel {

    private HttpRequest request;

    public RequestHttpModelImpl() {
        request = new HttpRequest();
    }



    private void onPinParam(BaseView view, Map<String, String> param) throws Exception {

        MobclickAgent.onEvent(view.getContext(), "request_count");
        //新增参数
//        param.put(ResponseKey.device, SystemUtils.getIMEI());
//        param.put(ResponseKey.app_type,"android");
//
//        Map<String, String> p1 = MapSortUtils.sortMapByKey(param);
//        Iterator<Map.Entry<String, String>> it = p1.entrySet().iterator();
//        StringBuffer sb = new StringBuffer();
//        while (it.hasNext()){
//            Map.Entry<String, String> entry = it.next();
//            sb.append(entry.getKey()+"="+ URLEncoder.encode(entry.getValue(),"UTF-8"));
//            sb.append("&");
//        }
//        sb.append(Constant.privateKey);
//        LogUtils.d("密文拼接： "+sb.toString());
//        String sign = MD5Util.encrypt(sb.toString()).toLowerCase();
//        param.put(ResponseKey.sign, sign);

    }


    @Override
    public void onSendGetHttp(final BaseView view, String url, Map<String, String> param, boolean isIntercept, final OnResponseCallBack<Map<String, Object>> callBack) throws Exception {

        onPinParam(view, param);
        if (view != null)
            view.showLoadding();
        request.onSendGetRequest(url, param, isIntercept, new OnMvpCallBack<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> data) {
                    callBack.onResponse(data);
            }

            @Override
            public void onFailure(String msg) {
                if (view != null)
                    ToastUtils.onToast(msg);
            }

            @Override
            public void onOutTime(String msg) {
                if (view != null)
                    ToastUtils.onToast(msg);
            }

            @Override
            public void onComplete() {
                if (view != null)
                    view.dismissLoadding();
            }
        });
    }

    @Override
    public void onSendPostHttp(final BaseView view, String url, Map<String, String> param, boolean isIntercept, final OnResponseCallBack<Map<String, Object>> callBack) throws Exception {
        onPinParam(view, param);
        if (view != null)
            view.showLoadding();
        request.onSendPostRequest(url, param, isIntercept, new OnMvpCallBack<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> data) {
                callBack.onResponse(data);
            }

            @Override
            public void onFailure(String msg) {
                if (view != null)
                    ToastUtils.onToast(msg);
            }

            @Override
            public void onOutTime(String msg) {
                if (view != null)
                    ToastUtils.onToast(msg);
            }

            @Override
            public void onComplete() {
                if (view != null)
                    view.dismissLoadding();
            }
        });
    }
}
