package com.dev.brian.business.network.http;

import com.dev.brian.business.module.recommand.BaseRecommandModel;
import com.dev.brian.sdk.okhttp.CommonOkHttpClient;
import com.dev.brian.sdk.okhttp.HttpConstant;
import com.dev.brian.sdk.okhttp.listener.DisposeDataHandle;
import com.dev.brian.sdk.okhttp.listener.DisposeDataListener;
import com.dev.brian.sdk.okhttp.request.CommonRequest;
import com.dev.brian.sdk.okhttp.request.RequestParams;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/16 0016
 * Description: 存放应用中所有的请求
 */

public class RequestCenter {
    // 根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params,
                                   DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 真正的发送我们的首页请求
     *
     * @param listener
     */
    public static void requestRecommandData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null,
                listener, BaseRecommandModel.class);
    }
}
