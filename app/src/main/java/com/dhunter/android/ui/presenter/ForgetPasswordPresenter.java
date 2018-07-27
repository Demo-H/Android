package com.dhunter.android.ui.presenter;

import com.dhunter.android.entity.BaseResponse;
import com.dhunter.android.entity.login.LoginRequest;
import com.dhunter.android.http.LoginDataManager;
import com.dhunter.android.ui.contract.ForgetPasswordContract;
import com.dhunter.common.base.BasePresenter;
import com.dhunter.common.network.ErrorDisposableObserver;

import javax.inject.Inject;

/**
 * Created by dhunter on 2018/7/6.
 */

public class ForgetPasswordPresenter extends BasePresenter implements ForgetPasswordContract.Presenter {

    private static final String TAG = "ForgetPasswordPresenter";
    private LoginDataManager mDataManager;
    private ForgetPasswordContract.View mView;

    @Inject
    public ForgetPasswordPresenter(LoginDataManager mDataManager, ForgetPasswordContract.View view) {
        this.mDataManager = mDataManager;
        this.mView = view;
    }

    @Override
    public void getSMSCode(String phoneNum) {
        addDisposabe(mDataManager.getSMSCode(new ErrorDisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                mView.hideDialog();
                mView.setSMSCodeSuccess();
                mView.toast(response.getMessage());
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
        }, phoneNum));
    }

    @Override
    public void forgetResetPwd(LoginRequest request) {
        addDisposabe(mDataManager.forgetResetPwd(new ErrorDisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse response) {
                mView.hideDialog();
                if(response != null) {
                    mView.toast(response.getMessage());
                    if(response.isSuccess()) {
                        mView.setResetPwdSuccess();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.hideDialog();
            }

            @Override
            public void onComplete() {

            }
        }, request));
    }
}
