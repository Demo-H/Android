package com.dhunter.android.http.api;

import com.dhunter.android.entity.FindNewsBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by dhunter on 2018/6/27.
 */

public interface NewsApiService {

    @GET("world/")
    Observable<FindNewsBean> getWorldNewsData(@QueryMap Map<String, Object> map);

    @GET("guonei/")
    Observable<FindNewsBean> getGuoneiNewsData(@QueryMap Map<String, Object> map);

    @GET("mobile/")
    Observable<FindNewsBean> getMobileNewsData(@QueryMap Map<String, Object> map);

    @GET("tiyu/")
    Observable<FindNewsBean> getTiyuNewsData(@QueryMap Map<String, Object> map);

    @GET("keji/")
    Observable<FindNewsBean> getKejiNewsData(@QueryMap Map<String, Object> map);
}
