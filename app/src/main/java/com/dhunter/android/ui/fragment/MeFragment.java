package com.dhunter.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhunter.android.R;
import com.dhunter.android.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by dhunter on 2018/6/25.
 */

public class MeFragment extends BaseFragment {

    private View rootView = null;

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

    }

    @Override
    public void requestData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }
}