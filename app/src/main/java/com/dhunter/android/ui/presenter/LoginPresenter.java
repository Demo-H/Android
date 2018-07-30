package com.dhunter.android.ui.presenter;

import com.dhunter.android.entity.BaseResponse;
import com.dhunter.android.entity.login.LoginRequest;
import com.dhunter.android.http.LoginDataManager;
import com.dhunter.android.ui.contract.LoginContract;
import com.dhunter.common.base.BasePresenter;
import com.dhunter.common.network.ErrorDisposableObserver;

import javax.inject.Inject;

/**
 * Created by dhunter on 2018/6/25.
 */

public class LoginPresenter extends BasePresenter implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";
    private LoginContract.View mView;
    private LoginDataManager mDataManager;

    @Inject
    public LoginPresenter(LoginDataManager dataManager, LoginContract.View view) {
        this.mDataManager = dataManager;
        this.mView = view;
    }

    @Override
    public void login(LoginRequest request) {
        addDisposabe(mDataManager.login(new ErrorDisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                mView.hideDialog();
                mView.setLoginResult(response);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.hideDialog();
                mView.setLoginResult(null);
            }

            @Override
            public void onComplete() {

            }
        }, request));
    }
}
