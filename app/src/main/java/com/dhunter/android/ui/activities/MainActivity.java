package com.dhunter.android.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.dhunter.android.R;
import com.dhunter.android.adapter.MainPagerAdapter;
import com.dhunter.android.base.BaseActivity;
import com.dhunter.android.base.BaseFragment;
import com.dhunter.android.entity.TabEntity;
import com.dhunter.android.ui.fragment.ShopCartFragment;
import com.dhunter.android.ui.fragment.ClassificationFragment;
import com.dhunter.android.ui.fragment.FindFragment;
import com.dhunter.android.ui.fragment.HomeFragment;
import com.dhunter.android.ui.fragment.MeFragment;
import com.dhunter.common.tablayout.CommonTabLayout;
import com.dhunter.common.tablayout.listener.CustomTabEntity;
import com.dhunter.common.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by dhunter on 2018/6/22.
 * 主界面
 */

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.main_tl)
    CommonTabLayout mTablayout;
    private MainPagerAdapter mAdapter;

    private HomeFragment mHomeFragment;
    private FindFragment mFindFragment;
    private ClassificationFragment mClassificationFragment;
    private ShopCartFragment mShopCartFragment;
    private MeFragment mMeFragment;
    private List<BaseFragment> fragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"首页", "分类", "发现", "购物车", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.nav_home_normal_ic,R.mipmap.nav_classification_normal_ic,  R.mipmap.nav_find_normal_ic,
            R.mipmap.nav_shopcart_normal_ic, R.mipmap.nav_me_normal_ic};
    private int[] mIconSelectIds = {
            R.mipmap.nav_home_selected_ic, R.mipmap.nav_classification_selected_ic, R.mipmap.nav_find_selected_ic,
            R.mipmap.nav_shopcart_selected_ic, R.mipmap.nav_me_selected_ic};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initLayout() {
        setFragments();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);

        mTablayout.setTabData(mTabEntities);
        mTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTablayout.showMsg(0, new Random().nextInt(100) + 1);
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTablayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);

        /**
        //两位数
        mTablayout.showMsg(0, 55);
        mTablayout.setMsgMargin(0, -5, 5);

        //三位数
        mTablayout.showMsg(1, 100);
        mTablayout.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        mTablayout.showDot(2);
        MsgView rtv_2_2 = mTablayout.getMsgView(2);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2, DisplayUtils.dip2px(mContext, 7.5f));
        }

        //设置未读消息背景
        mTablayout.showMsg(3, 5);
        mTablayout.setMsgMargin(3, 0, 5);
        MsgView rtv_2_3 = mTablayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
        */
    }

    @Override
    protected void requestData() {

    }

    private void setFragments() {
        mHomeFragment = new HomeFragment();
        mClassificationFragment = new ClassificationFragment();
        mFindFragment = new FindFragment();
        mShopCartFragment = new ShopCartFragment();
        mMeFragment = new MeFragment();
        fragments.add(mHomeFragment);
        fragments.add(mClassificationFragment);
        fragments.add(mFindFragment);
        fragments.add(mShopCartFragment);
        fragments.add(mMeFragment);
    }

}