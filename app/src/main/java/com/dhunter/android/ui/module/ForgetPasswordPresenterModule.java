package com.dhunter.android.ui.module;


import com.dhunter.android.http.LoginDataManager;
import com.dhunter.android.ui.contract.ForgetPasswordContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dhunter on 2018/7/6.
 */

@Module
public class ForgetPasswordPresenterModule {
    private ForgetPasswordContract.View view;
    private LoginDataManager mDataManager;

    public ForgetPasswordPresenterModule(ForgetPasswordContract.View view, LoginDataManager mDataManager) {
        this.view = view;
        this.mDataManager = mDataManager;
    }

    @Provides
    LoginDataManager providerLoginDataManager() {
        return mDataManager;
    }

    @Provides
    ForgetPasswordContract.View providerForgetPasswordContractView(){
        return view;
    }
}