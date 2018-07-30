package com.dhunter.android.http.api;

import com.dhunter.android.entity.BaseResponse;
import com.dhunter.android.entity.login.LoginRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dhunter on 2018/7/25.
 */

public interface LoginService {

    /** 登录接口 **/
    @POST("login")
    Observable<BaseResponse> login(@Body LoginRequest request);

    /** 发送获取验证码 **/
    @GET("smscode")
    Observable<BaseResponse> getSMSCode(@Query("phone") String phone);

    /** 忘记密码,修改密码 **/
    @POST("modified_password")
    Observable<BaseResponse> forgetResetPwd(@Body LoginRequest request);

    /** 注册 **/
    @POST("modified_password")
    Observable<BaseResponse> startRegister(@Body LoginRequest request);
}
