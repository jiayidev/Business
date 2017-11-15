package com.dev.brian.business.application;

import android.app.Application;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/15 0015
 * Description: 1.他是整个程序的入口
 *              2.初始化工作
 *              3.为整个应用的其它模块提供上下文
 */

public class BrianApplication extends Application {
    private static Application mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static Application getInstance(){
        return mApplication;
    }
}
