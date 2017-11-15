package com.dev.brian.business.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/15 0015
 * Description: 主要就是为我们所有的fragment提供公共的行为或事件
 */

public class BaseFragment extends Fragment {
    protected Activity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
