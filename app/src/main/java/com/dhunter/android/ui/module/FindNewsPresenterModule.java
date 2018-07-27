package com.dhunter.android.ui.module;

import com.dhunter.android.http.NewsDataManager;
import com.dhunter.android.ui.contract.FindNewsContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dhunter on 2018/6/27.
 */

@Module
public class FindNewsPresenterModule {
    private FindNewsContract.View view;
    private NewsDataManager mDataManager;

    public FindNewsPresenterModule(FindNewsContract.View  view, NewsDataManager mainDataManager) {
        this.view = view;
        this.mDataManager = mainDataManager;
    }

    @Provides
    FindNewsContract.View providerFindNewsContractView(){
        return view;
    }
    @Provides
    NewsDataManager providerNewsDataManager(){
        return mDataManager;
    }
}