package com.charge.wsq.app.mvp.presenter;
import com.charge.wsq.app.mvp.model.impl.RequestHttpModelImpl;
import com.charge.wsq.app.mvp.model.impl.DefaultModelImpl;
import com.charge.wsq.app.mvp.model.inter.RequestHttpModel;
import com.charge.wsq.app.mvp.model.inter.DefaultModel;
import com.charge.wsq.app.mvp.view.BaseView;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class DefaultPresenter<T extends BaseView> extends BasePresenter<T> {

    private DefaultModel defaultModel;
    private RequestHttpModel requestHttp;

    public DefaultPresenter() {
        requestHttp = new RequestHttpModelImpl();
        defaultModel = new DefaultModelImpl();
    }

}
