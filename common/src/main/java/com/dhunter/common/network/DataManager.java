package com.dhunter.common.network;

import android.content.Context;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by dhunter on 2018/6/25.
 */

@Singleton
public class DataManager {

    private HttpHelper httpHelper;

    private SharePreferenceHelper sharePreferenceHelper;

    private DataBaseHelper dataBaseHelper;

    private Context context;


    @Inject
    public DataManager( Context context ,HttpHelper httpHelper , SharePreferenceHelper sharePreferenceHelper
            , DataBaseHelper dataBaseHelper) {
        this.context = context;
        this.httpHelper = httpHelper;
        this.sharePreferenceHelper = sharePreferenceHelper;
        this.dataBaseHelper = dataBaseHelper;
    }



    public <S> S getService(Class<S> serviceClass){
        return httpHelper.getService(serviceClass);
    }


    public void saveSPData(String key , String value){
        sharePreferenceHelper.saveData(key , value);
    }

    public void saveSPMapData(Map<String,String> map){
        sharePreferenceHelper.saveData(map);
    }

    public String getSPData(String key){
        return sharePreferenceHelper.getValue(key);
    }

    public void deleteSPData(){
        sharePreferenceHelper.deletePreference();
    }

    public Map<String ,String> getSPMapData(){
        return sharePreferenceHelper.readData();
    }


    public Context getContext() {
        return context;
    }

}
