package com.dhunter.android.ui.presenter;

import com.dhunter.android.entity.BaseResponse;
import com.dhunter.android.entity.login.LoginRequest;
import com.dhunter.android.http.LoginDataManager;
import com.dhunter.android.ui.contract.RegisterContract;
import com.dhunter.common.base.BasePresenter;
import com.dhunter.common.network.ErrorDisposableObserver;

import javax.inject.Inject;

/**
 * Created by dhunter on 2018/7/10.
 */

public class RegisterPresenter extends BasePresenter implements RegisterContract.Presenter {

    private static final String TAG = "RegisterPresenter";
    private LoginDataManager mDataManager;
    private RegisterContract.View mView;

    @Inject
    public RegisterPresenter(LoginDataManager dataManager, RegisterContract.View view) {
        this.mDataManager = dataManager;
        this.mView = view;
    }

    @Override
    public void getSMSCode(String phone) {
        addDisposabe(mDataManager.getSMSCode(new ErrorDisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse responseBean) {
                mView.hideDialog();
                if(responseBean != null) {
                    mView.toast(responseBean.getMessage());
                    if(responseBean.isSuccess()) {
                        mView.setSmsCodeResult();
                    } else {
                        mView.setRegisterError();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.hideDialog();
                mView.setRegisterError();
            }

            @Override
            public void onComplete() {

            }
        }, phone));

    }

    @Override
    public void startRegister(LoginRequest request) {
        addDisposabe(mDataManager.startRegister(new ErrorDisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse responseBean) {
                mView.hideDialog();
                if(responseBean != null) {
                    mView.toast(responseBean.getMessage());
                    if(responseBean.isSuccess()) {
                        mView.setRegisterResult();
                    } else {
                        mView.setSMSCodeError();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.hideDialog();
                mView.setSMSCodeError();
            }

            @Override
            public void onComplete() {

            }
        }, request));
    }
}
