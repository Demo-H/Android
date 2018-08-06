package com.dhunter.android.ui.contract;

import java.util.List;

/**
 * Created by dhunter on 2018/8/3.
 */

public class ClassificationContract {

    public interface View {
        void setTypeOfNameData(List<String> list);

        void setTypeIconsData(String text);

    }

    public interface Presenter {
        void getTypeOfNameData();

        void getTypeIconsData();
    }
}
