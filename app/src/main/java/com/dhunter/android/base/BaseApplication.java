package com.dhunter.android.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.dhunter.common.GlobalAppComponent;
import com.dhunter.common.utils.FileUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by dhunter on 2018/6/25.
 */

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        if(mInstance == null){
            mInstance = this;
        }
        init();
    }

    private void init() {
        /**
         * 初始化SDK卡目录
         */
        FileUtils.initSdcardDirs(this);

        /**内存泄漏初始化**/
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        Fresco.initialize(this);
        GlobalAppComponent.init(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }
}
