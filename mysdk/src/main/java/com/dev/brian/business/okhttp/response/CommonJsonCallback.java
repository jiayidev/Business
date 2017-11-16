package com.dev.brian.business.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.dev.brian.business.okhttp.exception.OkHttpException;
import com.dev.brian.business.okhttp.listener.DisposeDataHandle;
import com.dev.brian.business.okhttp.listener.DisposeDataListener;
import com.dev.brian.business.util.ResponseEntityToModule;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/16 0016
 * Description: 专门处理Json的回调
 */

public class CommonJsonCallback implements Callback {

    // 与服务器返回的字段的一个对应关系
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it

    // 自定义异常类型
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler; // 进行一个消息的转发
    private DisposeDataListener mListener;
    private Class<?> mClass;


    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    // 请求失败处理
    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    // 真正的响应处理函数
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handlerResponse(result);
            }
        });
    }

    /**
     * 处理服务器返回的响应数据
     *
     * @param responseObj
     */
    private void handlerResponse(String responseObj) {
        // 为了保证代码的健壮性
        if (responseObj == null && responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        try {
            JSONObject result = new JSONObject(responseObj.toString());
            // 开始尝试解析json
            if (result.has(RESULT_CODE)) {
                // 从json对象中抽取出我们的响应码，若为0，则是正确的响应
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    // 不需要解析，直接返回数据到应用层
                    if (mClass == null) {
                        mListener.onSuccess(responseObj);
                    } else {
                        // 即，需要我们将json对象转化为实体对象
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(
                                result, mClass);
                        // 表明正确的转为了实体对象
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            // 返回的不是合法的json
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                }
            } else {
                // 将服务器返回给我们的异常回调到应用处去处理
                mListener.onFailure(new OkHttpException(OTHER_ERROR, result.get(RESULT_CODE)));
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }
    }

}
