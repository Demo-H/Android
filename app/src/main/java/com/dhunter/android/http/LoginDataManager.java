package com.dhunter.android.http;

import com.dhunter.android.entity.BaseResponse;
import com.dhunter.android.entity.login.LoginRequest;
import com.dhunter.android.http.api.LoginService;
import com.dhunter.common.network.DataManager;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by dhunter on 2018/7/25.
 * 网络请求不需要token头参数的接口放于该类
 */

public class LoginDataManager extends BaseDataManager {

    public LoginDataManager(DataManager mDataManager) {
        super(mDataManager);
    }

    public static LoginDataManager getInstance(DataManager dataManager){
        return new LoginDataManager(dataManager);
    }

    /**
     *验证短信验证码注册/登陆 （只做示例，无数据返回）
     **/

    public Disposable login(DisposableObserver<BaseResponse> consumer, LoginRequest request) {
        Observable observable = getService(LoginService.class).login(request);
        return changeIOToMainThread(observable, consumer);
    }

    /**
     * 忘记密码--获取验证码
     * @param consumer
     * @param phoneNum
     * @return
     */
    public Disposable getSMSCode(DisposableObserver<BaseResponse> consumer, String phoneNum) {
        Observable observable = getService(LoginService.class).getSMSCode(phoneNum);
        return changeIOToMainThread(observable, consumer);
    }

    /**
     * 忘记密码--设置新密码
     * @param consumer
     * @param request
     * @return
     */
    public Disposable forgetResetPwd(DisposableObserver<BaseResponse> consumer, LoginRequest request) {
        Observable observable = getService(LoginService.class).forgetResetPwd(request);
        return changeIOToMainThread(observable, consumer);
    }

    /**
     * 注册
     * @param consumer
     * @param request
     * @return
     */
    public Disposable startRegister(DisposableObserver<BaseResponse> consumer, LoginRequest request) {
        Observable observable = getService(LoginService.class).startRegister(request);
        return changeIOToMainThread(observable, consumer);
    }

}
