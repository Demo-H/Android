package com.dhunter.android.http;

import com.dhunter.common.network.DataManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhunter on 2018/6/25.
 */

public class MainDataManager extends BaseDataManager {

    public MainDataManager(DataManager mDataManager) {
        super(mDataManager);
    }

    public static MainDataManager getInstance(DataManager dataManager){
        return new MainDataManager(dataManager);
    }


    public List<String> getTypeOfNameData(){
        ArrayList<String> list = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            char c = (char)(i + 65);
            list.add("分类物品" + c);
        }
        return list;
    }

}
