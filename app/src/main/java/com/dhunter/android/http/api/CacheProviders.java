package com.dhunter.android.http.api;

import com.dhunter.android.entity.FindNewsBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Created by dhunter on 2018/6/25.
 */

public interface CacheProviders {

    //缓存时间 1天
    @LifeCache(duration = 1, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<FindNewsBean>>> getWorldNewsTypes(Observable observable, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 1, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<FindNewsBean>>> getGuoneiNewsTypes(Observable observable, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 1, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<FindNewsBean>>> getMobileNewsTypes(Observable observable, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 1, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<FindNewsBean>>> getTiyuNewsTypes(Observable observable, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 1, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<FindNewsBean>>> getKejiNewsTypes(Observable observable, DynamicKey userName, EvictDynamicKey evictDynamicKey);

}
