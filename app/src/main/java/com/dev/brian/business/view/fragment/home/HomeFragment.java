package com.dev.brian.business.view.fragment.home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.brian.business.R;
import com.dev.brian.business.adapter.CourseAdapter;
import com.dev.brian.business.module.recommand.BaseRecommandModel;
import com.dev.brian.business.network.http.RequestCenter;
import com.dev.brian.business.view.fragment.BaseFragment;
import com.dev.brian.business.view.home.HomeHeaderLayout;
import com.dev.brian.sdk.okhttp.listener.DisposeDataListener;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/15 0015
 * Description:
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    /**
     * UI
     */
    private View mContentView;
    private ListView mListView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;

    /**
     * data
     */
    private CourseAdapter mAdapter;
    private BaseRecommandModel mRecommandData;


    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mContentView;
    }

    private void initView() {
        mCategoryView = mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        mLoadingView = mContentView.findViewById(R.id.loading_view);
        // 启动我们的LoadingView动画
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }

    /**
     * 发送首页列表数据请求
     */
    private void requestRecommandData() {
        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.e(TAG, "onSuccess: " + responseObj.toString());
                /**
                 * 获取到数据后更新我们的UI
                 */
                mRecommandData = (BaseRecommandModel) responseObj;
                // 成功
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {
                // 提示用户网络有问题
                Log.e(TAG, "onFailure: " + reasonObj.toString());
            }
        });
    }

    /**
     * 请求成功执行的方法
     */
    private void showSuccessView() {
        // 判断数据是否为空
        if (mRecommandData.data.list != null && mRecommandData.data.list.size() > 0) {

            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);

            // 为ListView添加表头
            mListView.addHeaderView(new HomeHeaderLayout(mContext, mRecommandData.data.head));
            // 创建我们的Adapter
            mAdapter = new CourseAdapter(mContext, mRecommandData.data.list);
            mListView.setAdapter(mAdapter);
        } else {
            showErrorView();
        }
    }

    /**
     * 请求失败后执行的方法
     */
    private void showErrorView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
