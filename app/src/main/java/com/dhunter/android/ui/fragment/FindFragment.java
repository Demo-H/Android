package com.dhunter.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhunter.android.R;
import com.dhunter.android.adapter.FindAdapter;
import com.dhunter.android.base.BaseFragment;
import com.dhunter.android.utils.TabLayoutUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dhunter on 2018/6/25.
 */

public class FindFragment extends BaseFragment {

    @BindView(R.id.news_status_tab)
    TabLayout mTab;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private View rootView = null;
    private FindAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initLayout();
        requestData();
        return rootView;
    }

    @Override
    public void initLayout() {
        mAdapter = new FindAdapter(rootView.getContext(), getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mViewPager);
        TabLayoutUtils.getInstance().setIndicator(mTab);
    }

    @Override
    public void requestData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }
}