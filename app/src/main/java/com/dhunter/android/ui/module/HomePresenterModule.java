package com.dhunter.android.ui.module;

import com.dhunter.android.http.MainDataManager;
import com.dhunter.android.ui.contract.HomeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dhunter on 2018/6/27.
 */

@Module
public class HomePresenterModule {
    private HomeContract.View view;
    private MainDataManager mDataManager;

    public HomePresenterModule(HomeContract.View  view, MainDataManager mainDataManager) {
        this.view = view;
        this.mDataManager = mainDataManager;
    }

    @Provides
    HomeContract.View providerHomeContractView(){
        return view;
    }
    @Provides
    MainDataManager providerMainDataManager(){
        return mDataManager;
    }
}