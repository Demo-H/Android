package com.dhunter.android.http;

import com.dhunter.android.config.Constant;
import com.dhunter.android.entity.FindNewsBean;
import com.dhunter.android.http.api.NewsApiService;
import com.dhunter.common.network.DataManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;

/**
 * Created by dhunter on 2018/6/27.
 */

public class NewsDataManager extends BaseDataManager {

    public NewsDataManager(DataManager mDataManager) {
        super(mDataManager);
    }

    public static NewsDataManager getInstance(DataManager dataManager){
        return new NewsDataManager(dataManager);
    }

    public Disposable getNewsData(DisposableObserver<FindNewsBean> consumer, int page, int postion) {
        switch (postion) {
            case 0:
                return getWorldNewsData(consumer, page);
            case 1:
                return getGuoneiNewsData(consumer,page);
            case 2:
                return getMobileNewsData(consumer,page);
            case 3:
                return getTiyuNewsData(consumer, page);
            case 4:
                return getKejiNewsData(consumer, page);
            default:
                return getWorldNewsData(consumer, page);
        }
    }

    public Disposable getWorldNewsData(DisposableObserver<FindNewsBean> consumer, int page) {
        Observable observable = getService(NewsApiService.class).getWorldNewsData(getQueryMap(page));
        Observable observableCahce = providers.getWorldNewsTypes(observable, new DynamicKey("world_news"), new EvictDynamicKey(true)).map(new HttpResultFuncCache<List<FindNewsBean>>());
        return changeIOToMainThread(observableCahce, consumer);
    }

    public Disposable getGuoneiNewsData(DisposableObserver<FindNewsBean> consumer, int page) {
        Observable observable = getService(NewsApiService.class).getGuoneiNewsData(getQueryMap(page));
        Observable observableCahce = providers.getGuoneiNewsTypes(observable, new DynamicKey("guonei_news"), new EvictDynamicKey(true)).map(new HttpResultFuncCache<List<FindNewsBean>>());
        return changeIOToMainThread(observableCahce, consumer);
    }

    public Disposable getMobileNewsData(DisposableObserver<FindNewsBean> consumer, int page) {
        Observable observable = getService(NewsApiService.class).getMobileNewsData(getQueryMap(page));
        Observable observableCahce = providers.getMobileNewsTypes(observable, new DynamicKey("mobile_news"), new EvictDynamicKey(true)).map(new HttpResultFuncCache<List<FindNewsBean>>());
        return changeIOToMainThread(observableCahce, consumer);
    }

    public Disposable getTiyuNewsData(DisposableObserver<FindNewsBean> consumer, int page) {
        Observable observable = getService(NewsApiService.class).getTiyuNewsData(getQueryMap(page));
        Observable observableCahce = providers.getTiyuNewsTypes(observable, new DynamicKey("tiyu_news"), new EvictDynamicKey(true)).map(new HttpResultFuncCache<List<FindNewsBean>>());
        return changeIOToMainThread(observableCahce, consumer);
    }

    public Disposable getKejiNewsData(DisposableObserver<FindNewsBean> consumer, int page) {
        Observable observable = getService(NewsApiService.class).getKejiNewsData(getQueryMap(page));
        Observable observableCahce = providers.getKejiNewsTypes(observable, new DynamicKey("keji_news"), new EvictDynamicKey(true)).map(new HttpResultFuncCache<List<FindNewsBean>>());
        return changeIOToMainThread(observableCahce, consumer);
    }

    private Map<String, Object> getQueryMap(int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", Constant.APIKEY);
        map.put("page", page);
        map.put("num", Constant.DEFAULT_PAGE_NUMBER);
        return map;
    }

}
