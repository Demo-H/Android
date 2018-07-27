package com.dhunter.android.http;

import android.content.Context;

import com.dhunter.android.http.api.CacheProviders;
import com.dhunter.android.http.retrofit.GsonTSpeaker;
import com.dhunter.common.network.DataManager;
import com.dhunter.common.utils.FileUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;

/**
 * Created by dhunter on 2018/6/25.
 */

public class BaseDataManager {

    private DataManager mDataManager;
    protected static File cacheDirectory = FileUtils.getcacheDirectory();
    protected static final CacheProviders providers  = new RxCache.Builder()
            .persistence(cacheDirectory, new GsonTSpeaker())
            .using(CacheProviders.class);

    public BaseDataManager(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    protected void saveSPData(String key , String value){
        mDataManager.saveSPData(key,value);
    }

    public void saveSPMapData(Map<String,String> map){
        mDataManager.saveSPMapData(map);
    }

    public String getSPData(String key){
        return mDataManager.getSPData(key);
    }

    public void deleteSPData(){
        mDataManager.deleteSPData();
    }

    public Map<String ,String> getSPMapData(){
        return mDataManager.getSPMapData();
    }

    protected<S> Disposable changeIOToMainThread(Observable<S> observable , DisposableObserver<S> consumer ){
        return observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread()) //回调到主线程
                .subscribeWith(consumer);
    }

    protected  <S> S getService(Class<S> serviceClass){
        return mDataManager.getService(serviceClass);
    }

    protected Context getContext(){
        return mDataManager.getContext();
    }

    /**
     * 用来统一处理RxCacha的结果
     */
    public class HttpResultFuncCache<T> implements Function<Reply<T>, T> {

        @Override
        public T apply(@NonNull Reply<T> httpResult) throws Exception {
            return httpResult.getData();
        }
    }

    /**
     * 读取本地json数据，模拟从服务器返回的数据
     * @param consumer
     * @param clazz
     * @param fillName
     * @param <S>
     * @return
     */
    public<S> Disposable getData(DisposableObserver<S> consumer , final Class<S> clazz , final String fillName) {
        return Observable.create(new ObservableOnSubscribe<S>() {
            @Override
            public void subscribe(ObservableEmitter<S> e) throws Exception {
                InputStream is = getContext().getAssets().open(fillName);
                String text = FileUtils.readTextFromFile(is);
                Gson gson = new Gson();
                e.onNext(gson.fromJson(text, clazz));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(consumer);
    }
}
