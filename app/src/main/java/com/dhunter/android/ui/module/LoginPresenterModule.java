package com.dhunter.android.ui.module;

import com.dhunter.android.http.LoginDataManager;
import com.dhunter.android.ui.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dhunter on 2018/6/26.
 */

@Module
public class LoginPresenterModule {
    private LoginContract.View mView;
    private LoginDataManager mDataManager;

    public LoginPresenterModule(LoginContract.View view, LoginDataManager mainDataManager) {
        this.mView = view;
        this.mDataManager = mainDataManager;
    }

    @Provides
    LoginDataManager providerLoginDataManager() {
        return mDataManager;
    }

    @Provides
    LoginContract.View providerMainContractView(){
        return mView;
    }
}
