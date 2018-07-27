package com.dhunter.android.http;

import com.dhunter.android.http.api.MainApiService;
import com.dhunter.common.network.DataManager;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by dhunter on 2018/6/25.
 */

public class MainDataManager extends BaseDataManager {

    public MainDataManager(DataManager mDataManager) {
        super(mDataManager);
    }

    public static MainDataManager getInstance(DataManager dataManager){
        return new MainDataManager(dataManager);
    }



}
