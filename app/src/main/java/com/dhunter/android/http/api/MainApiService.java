package com.dhunter.android.http.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by dhunter on 2018/6/26.
 */

public interface MainApiService {

    @FormUrlEncoded
    @POST("userRegister/login")
    Observable<ResponseBody> login(@Field("mobile")String mobile, @Field("code")String psw);
}
