package com.dhunter.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dhunter.android.R;
import com.dhunter.android.adapter.TypeOfGoodsNameAdapter;
import com.dhunter.android.base.BaseFragment;
import com.dhunter.android.http.MainDataManager;
import com.dhunter.android.ui.component.DaggerClassificationFragmentComponent;
import com.dhunter.android.ui.contract.ClassificationContract;
import com.dhunter.android.ui.module.ClassificationPresenterModule;
import com.dhunter.android.ui.presenter.ClassificationPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dhunter on 2018/6/25.
 */

public class ClassificationFragment extends BaseFragment implements ClassificationContract.View {

    private View rootView = null;

    @BindView(R.id.scanning_img)
    ImageView scanningImg;
    @BindView(R.id.advisory_img)
    ImageView advisoryImg;
    @BindView(R.id.classic_recycle)
    RecyclerView typeOfGoodsNameView;
    @BindView(R.id.classic_icon_view)
    RecyclerView classicIconView;
    private TypeOfGoodsNameAdapter adapter;

    @Inject
    ClassificationPresenter presenter;

    public static ClassificationFragment newInstance() {
        ClassificationFragment classificationFragment = new ClassificationFragment();
        return classificationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initLayout();
        requestData();
        return rootView;
    }

    @Override
    public void initLayout() {
        DaggerClassificationFragmentComponent.builder()
                .appComponent(getAppComponent())
                .classificationPresenterModule(new ClassificationPresenterModule(this, MainDataManager.getInstance(mDataManager)))
                .build()
                .inject(this);
        typeOfGoodsNameView.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
        adapter = new TypeOfGoodsNameAdapter(R.layout.recycleview_type_of_goods_name_view_item);
        typeOfGoodsNameView.setAdapter(adapter);
    }

    @Override
    public void requestData() {
        presenter.getTypeOfNameData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_classification;
    }

    @Override
    public void setTypeOfNameData(final List<String> list) {
        adapter.addData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setTypeIconsData(final String text) {

    }
}