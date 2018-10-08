package com.charge.wsq.app.mvp.model.inter;

import com.charge.wsq.app.mvp.callback.OnResponseCallBack;
import com.charge.wsq.app.mvp.view.BaseView;
import java.util.Map;

public interface RequestHttpModel {


    void onSendGetHttp(BaseView view, String url, Map<String, String> param, boolean isIntercept, OnResponseCallBack<Map<String, Object>> callBack) throws Exception;

    /**
     * 正常请求-POST
     * @param view
     * @param url
     * @param param
     * @param isIntercept 是否拦截失败的情况
     * @param callBack
     * @throws Exception
     */
    void onSendPostHttp(BaseView view, String url, Map<String, String> param, boolean isIntercept, OnResponseCallBack<Map<String, Object>> callBack) throws Exception;


}
