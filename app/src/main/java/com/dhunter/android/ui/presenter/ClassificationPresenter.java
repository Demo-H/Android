package com.dhunter.android.ui.presenter;

import com.dhunter.android.http.MainDataManager;
import com.dhunter.android.ui.contract.ClassificationContract;
import com.dhunter.common.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by dhunter on 2018/8/3.
 */

public class ClassificationPresenter extends BasePresenter implements ClassificationContract.Presenter {

    private MainDataManager mDataManager;

    private ClassificationContract.View mClassificationView;
    @Inject
    public ClassificationPresenter(MainDataManager mDataManager, ClassificationContract.View view) {
        this.mDataManager = mDataManager;
        this.mClassificationView = view;

    }

    @Override
    public void getTypeOfNameData() {
        List<String> typeOfNameData = mDataManager.getTypeOfNameData();
        mClassificationView.setTypeOfNameData(typeOfNameData);
    }

    @Override
    public void getTypeIconsData() {

    }
}
