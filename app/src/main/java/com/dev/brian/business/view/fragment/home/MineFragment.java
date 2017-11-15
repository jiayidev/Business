package com.dev.brian.business.view.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class MineFragment extends BaseFragment {

    private View mContentView;

    public MineFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_mine_layout, null, false);
        initView();
        return mContentView;
    }

    private void initView() {

    }
}
