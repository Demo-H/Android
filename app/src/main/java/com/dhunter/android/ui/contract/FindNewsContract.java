package com.dhunter.android.ui.contract;

import com.dhunter.android.entity.FindNewsBean;

/**
 * Created by dhunter on 2018/6/27.
 */

public class FindNewsContract {
    public interface View {
        void setNewsData(FindNewsBean mBean);
        void setMoreNewsData(FindNewsBean mBean);
    }

    public interface Presenter {
        void getNewsData(int postion);
        void getMoreNewsData(int page, int postion);
    }
}
