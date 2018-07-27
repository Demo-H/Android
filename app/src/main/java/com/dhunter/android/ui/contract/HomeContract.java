package com.dhunter.android.ui.contract;

import com.dhunter.android.entity.HomeIndex;

/**
 * Created by dhunter on 2018/6/26.
 */

public class HomeContract {

    public interface View {
        void showToast(String string);
        void setNormalView();
        void setNetErrorView();
        void reLogin();
        void setHomeIndexData(HomeIndex mBean);
        void setRecommendedWares(HomeIndex mBean);
        void setMoreRecommendedWares(HomeIndex mBean);
    }

    public interface Presenter {
        void getHomeIndexData(int flag);
        void getRecommendedWares();
        void getMoreRecommendedWares();
    }
}
