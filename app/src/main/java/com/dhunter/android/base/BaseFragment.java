package com.dhunter.android.base;

import android.app.Activity;
import android.content.Context;

import com.dhunter.common.AppComponent;
import com.dhunter.common.GlobalAppComponent;
import com.dhunter.common.base.BaseRxFragment;
import com.dhunter.common.network.DataManager;

import butterknife.Unbinder;

/**
 * Created by dhunter on 2018/6/25.
 * 为添加butterknife控件，再封装一次
 */

public abstract class BaseFragment extends BaseRxFragment{

    protected Unbinder unbinder;
    protected DataManager mDataManager;
    protected Context mContext;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getAppComponent().getContext();
        mDataManager = getAppComponent().getDataManager();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
    }

    protected AppComponent getAppComponent() {
        return GlobalAppComponent.getAppComponent();
    }
}
