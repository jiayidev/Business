package com.dev.brian.business.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.brian.business.R;
import com.dev.brian.business.module.recommand.RecommandBodyValue;
import com.dev.brian.business.util.Util;
import com.dev.brian.sdk.imageloader.ImageLoaderManager;
import com.dev.brian.sdk.util.Utils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/17 0017
 * Description:
 */

public class CourseAdapter extends BaseAdapter {

    /**
     * ListView不同类型的Item标识
     */
    private static final int CARD_COUNT = 4;
    private static final int VIDEO_TYPE = 0x00;
    private static final int CARD_SIGNAL_PIC = 0x01;
    private static final int CARD_MULTI_PIC = 0x02;
    private static final int CARD_VIEW_PAGER = 0x03;

    private Context mContext;
    private ViewHolder mViewHolder = null;
    private LayoutInflater mInflate;

    private ArrayList<RecommandBodyValue> mData;

    /**
     * 异步图片加载工具类
     */
    private ImageLoaderManager mImageLoader;

    /**
     * 构造方法
     *
     * @param context 上下文
     * @param data    数据
     */
    public CourseAdapter(Context context, ArrayList<RecommandBodyValue> data) {
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);
        mImageLoader = ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return CARD_COUNT;
    }

    /**
     * 通知Adapter使用哪种类型的Item去加载数据
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        return value.type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1.获取数据的type类型
        int type = getItemViewType(position);
        Log.e("BUG", "getView: " + type);
        final RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        // 为空表明当前没有使用的缓存View
        if (convertView == null) {
            switch (type) {
                case CARD_SIGNAL_PIC:
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_product_card_one_layout, parent, false);
                    // 初始化ViewHolder中所有用到的控件
                    mViewHolder.mLogoView = convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mProductView = convertView.findViewById(R.id.product_photo_view);
                    mViewHolder.mPriceView = convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = convertView.findViewById(R.id.item_zan_view);
                    break;
                case CARD_MULTI_PIC:
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_product_card_two_layout, parent, false);
                    // 初始化多图ViewHolder中的View
                    mViewHolder.mLogoView = convertView.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitleView = convertView.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = convertView.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = convertView.findViewById(R.id.item_footer_view);
                    mViewHolder.mPriceView = convertView.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = convertView.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = convertView.findViewById(R.id.item_zan_view);
                    mViewHolder.mProductLayout = convertView.findViewById(R.id.product_photo_layout);
                    break;
                case CARD_VIEW_PAGER:
                    mViewHolder = new ViewHolder();
                    convertView = mInflate.inflate(R.layout.item_product_card_three_layout, parent, false);

                    mViewHolder.mViewPager = convertView.findViewById(R.id.pager);
                    mViewHolder.mViewPager.setPageMargin(Utils.dip2px(mContext, 12));
                    // 为我们的ViewPager填充数据
                    ArrayList<RecommandBodyValue> recommandList = Util.handleData(value);
                    mViewHolder.mViewPager.setAdapter(new HotSalePagerAdapter(mContext, recommandList));
                    // 一开始就让我们的ViewPager处于一个比较靠中间的Item项
                    mViewHolder.mViewPager.setCurrentItem(recommandList.size() * 100);
                    break;
            }
            convertView.setTag(mViewHolder);
        } else {// 有可用的ConvertView
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        // 开始绑定数据
        switch (type) {
            case CARD_SIGNAL_PIC:
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan).concat(value.zan));

                /**
                 * 位ImageView完成图片的加载
                 */
                mImageLoader.displayImage(mViewHolder.mLogoView, value.logo);
                mImageLoader.displayImage(mViewHolder.mProductView, value.url.get(0));
                break;
            case CARD_MULTI_PIC:
                // 为我们的多图item绑定数据
                mViewHolder.mTitleView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info.concat(mContext
                        .getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext
                        .getString(R.string.dian_zan).concat(value.zan));

                // 为我们的LogoView加载异步图片
                mImageLoader.displayImage(mViewHolder.mLogoView, value.logo);
                // 动态的添加我们的ImageView到我们的水平ScrollView中
                mViewHolder.mProductLayout.removeAllViews();// 删除已有的图片
                for (String url : value.url) {
                    mViewHolder.mProductLayout.addView(createImageView(url));
                }
                break;
        }
        return convertView;
    }

    /**
     * 动态的创建我们的ImageView
     *
     * @return
     */
    private ImageView createImageView(String url) {
        ImageView imageView = new ImageView(mContext);
        // 要与添加到的ViewGroup要保持一致
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Utils.dip2px(mContext, 100), LinearLayout.LayoutParams.MATCH_PARENT);
        params.leftMargin = Utils.dip2px(mContext, 5);
        mImageLoader.displayImage(imageView, url);
        return imageView;
    }

    /**
     * 用来缓存我们已经创建好的Item
     */
    private static class ViewHolder {
        // 所有Card公共属性
        private CircleImageView mLogoView;
        private TextView mTitleView;
        private TextView mInfoView;
        private TextView mFooterView;
        // Video Card特有属性
        private RelativeLayout mViewContentLayout;
        private ImageView mShareView;
        // Video Card外所有Card具有属性
        private TextView mPriceView;
        private TextView mFromView;
        private TextView mZanView;
        // Card One特有属性
        private LinearLayout mProductLayout;
        // Card Two特有属性
        private ImageView mProductView;
        // Card Three特有属性
        private ViewPager mViewPager;


    }
}
