package com.dhunter.android.ui.module;


import com.dhunter.android.http.LoginDataManager;
import com.dhunter.android.ui.contract.RegisterContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dhunter on 2018/7/10.
 */

@Module
public class RegisterPresenterModule {
    private RegisterContract.View view;
    private LoginDataManager mDataManager;

    public RegisterPresenterModule(RegisterContract.View view, LoginDataManager mDataManager) {
        this.view = view;
        this.mDataManager = mDataManager;
    }

    @Provides
    LoginDataManager providerLoginDataManager() {
        return mDataManager;
    }

    @Provides
    RegisterContract.View providerRegisterContractView(){
        return view;
    }
}