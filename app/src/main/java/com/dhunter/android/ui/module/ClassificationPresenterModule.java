package com.dhunter.android.ui.module;

import com.dhunter.android.http.MainDataManager;
import com.dhunter.android.ui.contract.ClassificationContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dhunter on 2018/8/3.
 */

@Module
public class ClassificationPresenterModule {
    private ClassificationContract.View  view;

    private MainDataManager mainDataManager;

    public ClassificationPresenterModule(ClassificationContract.View  view, MainDataManager mainDataManager) {
        this.view = view;
        this.mainDataManager = mainDataManager;
    }

    @Provides
    ClassificationContract.View providerMainContractView(){
        return view;
    }
    @Provides
    MainDataManager providerMainDataManager(){
        return mainDataManager;
    }
}
