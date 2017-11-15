package com.dev.brian.business.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.brian.business.R;
import com.dev.brian.business.activity.base.BaseActivity;
import com.dev.brian.business.view.fragment.home.HomeFragment;
import com.dev.brian.business.view.fragment.home.MessageFragment;
import com.dev.brian.business.view.fragment.home.MineFragment;

/**
 * @function 创建首页所有的fragment, 以及fragment
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    /**
     *  fragment相关
     */
    private FragmentManager fm;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;
    private Fragment mCurrent;

    /**
     *  UI
     */
    private RelativeLayout mHomeLayout;
    private RelativeLayout mPondLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMineLayout;
    private TextView mHomeView;
    private TextView mPondView;
    private TextView mMessageView;
    private TextView mMineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);

        // 初始化我们页面中所有的控件
        initView();

        // 添加默认要显示的fragment
        mHomeFragment = new HomeFragment();
        fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, mHomeFragment);
        fragmentTransaction.commit();
    }

    private void initView() {
        mHomeLayout = (RelativeLayout) findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);
        mPondLayout = (RelativeLayout) findViewById(R.id.pond_layout_view);
        mPondLayout.setOnClickListener(this);
        mMessageLayout = (RelativeLayout) findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMineLayout = (RelativeLayout) findViewById(R.id.mine_layout_view);
        mMineLayout.setOnClickListener(this);

        mHomeView = (TextView) findViewById(R.id.home_image_view);
        mPondView = (TextView) findViewById(R.id.fish_image_view);
        mMessageView = (TextView) findViewById(R.id.message_image_view);
        mMineView = (TextView) findViewById(R.id.mine_image_view);
        mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 用来隐藏具体的fragment
     *
     * @param fragment
     * @param ft
     */
    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.home_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                // 隐藏其他两个fragment
                hideFragment(mMessageFragment, fragmentTransaction);
                hideFragment(mMineFragment, fragmentTransaction);
                // 将我们的HomeFragment显示出来
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout, mHomeFragment);
                } else {
                    // 已经创建过了
                    fragmentTransaction.show(mHomeFragment);
                }
                break;
            case R.id.pond_layout_view:
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);
                break;
            case R.id.message_layout_view:
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                // 隐藏其他两个fragment
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mMineFragment, fragmentTransaction);
                // 将我们的MessageFragment显示出来
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.content_layout, mMessageFragment);
                } else {
                    // 已经创建过了
                    fragmentTransaction.show(mMessageFragment);
                }
                break;
            case R.id.mine_layout_view:
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);

                // 隐藏其他两个fragment
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mMessageFragment, fragmentTransaction);
                // 将我们的MineFragment显示出来
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.content_layout, mMineFragment);
                } else {
                    // 已经创建过了
                    fragmentTransaction.show(mMineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }
}
