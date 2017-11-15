package com.dev.brian.business.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/15 0015
 * Description: 是为我们所有的activity提供公共的行为
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 输出日志，所需tag
     */
    public String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
