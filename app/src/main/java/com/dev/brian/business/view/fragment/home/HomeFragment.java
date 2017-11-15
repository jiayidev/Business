package com.dev.brian.business.view.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.brian.business.R;
import com.dev.brian.business.view.fragment.BaseFragment;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/15 0015
 * Description:
 */

public class HomeFragment extends BaseFragment {

    private View mContentView;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mContentView;
    }

    private void initView() {

    }
}
