package com.dhunter.android.ui.module;

import com.dhunter.android.http.MainDataManager;
import com.dhunter.android.ui.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dhunter on 2018/6/26.
 */

@Module
public class LoginPresenterModule {
    private LoginContract.View mView;
    private MainDataManager mDataManager;

    public LoginPresenterModule(LoginContract.View view, MainDataManager mainDataManager) {
        this.mView = view;
        this.mDataManager = mainDataManager;
    }

    @Provides
    MainDataManager providerMainDataManager() {
        return mDataManager;
    }

    @Provides
    LoginContract.View providerMainContractView(){
        return mView;
    }
}
