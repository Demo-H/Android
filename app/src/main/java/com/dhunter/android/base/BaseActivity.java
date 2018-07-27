package com.dhunter.android.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.dhunter.common.AppComponent;
import com.dhunter.common.GlobalAppComponent;
import com.dhunter.common.base.BaseRxActivity;
import com.dhunter.common.network.DataManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dhunter on 2018/6/25.
 * 为添加butterknife控件，再封装一次
 */

public abstract class BaseActivity extends BaseRxActivity {

    protected Unbinder mUnbinder;
    protected Context mContext;
    protected DataManager mDataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        mDataManager = getAppComponent().getDataManager();
        initLayout();
        requestData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null)
            mUnbinder.unbind();
    }

    protected AppComponent getAppComponent() {
        return GlobalAppComponent.getAppComponent();
    }
}
