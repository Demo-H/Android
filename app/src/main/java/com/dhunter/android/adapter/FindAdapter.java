package com.dhunter.android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dhunter.android.R;
import com.dhunter.android.base.BaseFragment;
import com.dhunter.android.config.Constant;
import com.dhunter.android.ui.fragment.FindNewsFragment;

/**
 * Created by dhunter on 2018/6/27.
 */

public class FindAdapter extends FragmentPagerAdapter {

    private String[] mTilte;

    public FindAdapter(Context context, FragmentManager fm) {
        super(fm);
        mTilte = context.getResources().getStringArray(R.array.find_tab_title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTilte[position];
    }

    @Override
    public BaseFragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.FRAGMENT_TAB_POSITION, position);
        BaseFragment fragment = FindNewsFragment.newInstance(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTilte.length;
    }
}
