package com.dev.brian.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dev.brian.business.okhttp.CommonOkHttpClient;
import com.dev.brian.business.okhttp.listener.DisposeDataHandle;
import com.dev.brian.business.okhttp.listener.DisposeDataListener;
import com.dev.brian.business.okhttp.request.CommonRequest;
import com.dev.brian.business.okhttp.response.CommonJsonCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/16 0016
 * Description:
 */

public class BaseOkhttpTestActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 用okhttp发送一个最基本的请求
     */
    private void sendRequest(){
        // 创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        // 创建一个Request
        final Request request = new Request.Builder()
                .url("https://www.imooc.com")
                .build();
        // new call
        Call call = mOkHttpClient.newCall(request);
        // 请求加入调度
        call.enqueue(new Callback() {
            // 请求失败
            @Override
            public void onFailure(Call call, IOException e) {

            }

            // 请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void test(){
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest("http://www.imooc.com", null),
                new CommonJsonCallback(new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        })));
    }
}
