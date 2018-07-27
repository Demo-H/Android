package com.dhunter.android.ui.presenter;

import android.util.Log;

import com.dhunter.android.config.Constant;
import com.dhunter.android.entity.FindNewsBean;
import com.dhunter.android.http.NewsDataManager;
import com.dhunter.android.ui.contract.FindNewsContract;
import com.dhunter.android.utils.ObjectUtil;
import com.dhunter.common.base.BasePresenter;
import com.dhunter.common.network.ErrorDisposableObserver;

import javax.inject.Inject;

/**
 * Created by dhunter on 2018/6/27.
 */

public class FindNewsPresenter extends BasePresenter implements FindNewsContract.Presenter {
    private static final String TAG = "FindNewsPresenter";

    private NewsDataManager mDataManager;
    private FindNewsContract.View mView;

    @Inject
    public FindNewsPresenter(NewsDataManager mDataManager, FindNewsContract.View view) {
        this.mDataManager = mDataManager;
        this.mView = view;
    }


    @Override
    public void getNewsData(int postion) {
        addDisposabe(mDataManager.getNewsData(new ErrorDisposableObserver<FindNewsBean>() {
            @Override
            public void onNext(FindNewsBean findNewsBean) {
                String json = ObjectUtil.jsonFormatter(findNewsBean);
                mView.setNewsData(findNewsBean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.i(TAG, "error" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        }, Constant.FIRST_PAGE, postion));
    }

    @Override
    public void getMoreNewsData(int page, int postion) {
        addDisposabe(mDataManager.getNewsData(new ErrorDisposableObserver<FindNewsBean>() {
            @Override
            public void onNext(FindNewsBean findNewsBean) {
                String json = ObjectUtil.jsonFormatter(findNewsBean);
                mView.setNewsData(findNewsBean);
            }

            @Override
            public void onComplete() {

            }
        }, page, postion));
    }
}
