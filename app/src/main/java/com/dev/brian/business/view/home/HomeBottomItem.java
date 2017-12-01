package com.dev.brian.business.view.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.brian.business.R;
import com.dev.brian.business.module.recommand.RecommandFooterValue;
import com.dev.brian.sdk.imageloader.ImageLoaderManager;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/12/1 0001
 * Description:
 */

public class HomeBottomItem extends RelativeLayout {

    private Context mContext;
    /**
     * UI
     */
    private RelativeLayout mRootView;
    private TextView mTitleView;
    private TextView mInfoView;
    private TextView mInterestingView;
    private ImageView mImageOneView;
    private ImageView mImageTwoView;

    /**
     * Data
     */
    private RecommandFooterValue mData;
    private ImageLoaderManager mImageLoader;

    public HomeBottomItem(Context context, RecommandFooterValue data) {
        this(context, null, data);
    }

    public HomeBottomItem(Context context, AttributeSet attrs, RecommandFooterValue data) {
        super(context, attrs);
        mContext = context;
        mData = data;
        mImageLoader = ImageLoaderManager.getInstance(mContext);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        // 添加到当前布局中
        mRootView = (RelativeLayout) inflater.inflate(R.layout.item_home_recommand_layout, this);
        mTitleView = (TextView) mRootView.findViewById(R.id.title_view);
        mInfoView = (TextView) mRootView.findViewById(R.id.info_view);
        mInterestingView = (TextView) mRootView.findViewById(R.id.interesting_view);
        mImageOneView = (ImageView) mRootView.findViewById(R.id.icon_1);
        mImageTwoView = (ImageView) mRootView.findViewById(R.id.icon_2);

        // 绑定数据
        mTitleView.setText(mData.title);
        mInfoView.setText(mData.info);
        mInterestingView.setText(mData.from);
        mImageLoader.displayImage(mImageOneView, mData.imageOne);
        mImageLoader.displayImage(mImageTwoView, mData.imageTwo);
    }

}
