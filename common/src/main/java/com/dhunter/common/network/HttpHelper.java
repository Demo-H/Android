package com.dhunter.common.network;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.dhunter.common.config.GlobalConfig;
import com.dhunter.common.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dhunter on 2018/6/25.
 * 负责创建ApiService实例
 * 缓存已经添加  目前只支持GET请求的缓存
 */

@Singleton
public class HttpHelper {
    private Context context;
    private Retrofit mRetrofitClient;
    private HashMap<String, Object> mServiceMap;
    private static File cacheDirectory;
    private static Cache cache;

    @Inject
    public HttpHelper(Context context) {
        this.context = context;
        mServiceMap = new HashMap<>();
        //设置缓存目录
        cacheDirectory = FileUtils.getcacheDirectory();
        cache = new Cache(cacheDirectory, 100 * 1024 * 1024);
        initRetrofitClient();
    }


    @SuppressWarnings("unchecked")
    public <S> S getService(Class<S> serviceClass) {
        if (mServiceMap.containsKey(serviceClass.getName())) {
            return (S) mServiceMap.get(serviceClass.getName());
        } else {
            Object obj = createService(serviceClass, null);
            mServiceMap.put(serviceClass.getName(), obj);
            return (S) obj;
        }
    }

    @SuppressWarnings("unchecked")
    public <S> S getService(Class<S> serviceClass, OkHttpClient client) {
        if (mServiceMap.containsKey(serviceClass.getName())) {
            return (S) mServiceMap.get(serviceClass.getName());
        } else {
            Object obj = createService(serviceClass, client);
            mServiceMap.put(serviceClass.getName(), obj);
            return (S) obj;
        }
    }

    private void initRetrofitClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(GlobalConfig.HTTP_READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(GlobalConfig.HTTP_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(GlobalConfig.HTTP_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                // 错误重连
                .retryOnConnectionFailure(true)
                // 支持HTTPS ，明文Http与比较新的Https
                .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
                // cookie管理
//                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getInstance())))
                //插入Header
//                .addInterceptor(new BaseInterceptor<>(getHeader(),context))
                .addInterceptor(new HeaderInterceptor<>(getHeader(),context))
//                .addNetworkInterceptor(new HeaderInterceptor<>(getHeader(),context))
                .cache(cache)
                .build();
        mRetrofitClient = createRetrofitClient(httpClient);
    }

    private Retrofit createRetrofitClient(OkHttpClient httpClient) {

        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(GlobalConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    private <S> S createService(Class<S> serviceClass, OkHttpClient client){
        if(client == null){
            return mRetrofitClient.create(serviceClass);
        }else{
            return createRetrofitClient(client).create(serviceClass);
        }
    }

    /**
     * 添加公共头
     */
    private Map<String, String> getHeader() {
        Map<String, String> list = new ArrayMap<>();
        String token = "pEUe4uFJzww=";
        list.put("x_request_platform", "PC");
        list.put("x_auth_token",token);
        return list;
    }

}