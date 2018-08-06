package com.dhunter.android.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.dhunter.android.R;
import com.dhunter.android.adapter.HomeMultipleRecycleAdapter;
import com.dhunter.android.base.BaseFragment;
import com.dhunter.android.entity.HomeIndex;
import com.dhunter.android.http.MainDataManager;
import com.dhunter.android.ui.component.DaggerHomeFragmentComponent;
import com.dhunter.android.ui.contract.HomeContract;
import com.dhunter.android.ui.module.HomePresenterModule;
import com.dhunter.android.ui.presenter.HomePresenter;
import com.dhunter.android.utils.SpaceItemDecoration;
import com.dhunter.common.recycleview.BaseQuickAdapter;
import com.dhunter.common.utils.DisplayUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dhunter on 2018/6/25.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    /**
     * 改变titlebar中icon颜色时的距离
     */
    private static int DISTANCE_WHEN_TO_SELECTED = 40;
    private View rootView = null;
    @BindView(R.id.scanning_layout)
    LinearLayout scanningLayout;
    @BindView(R.id.advisory_layout)
    LinearLayout advisoryLayout;
    @BindView(R.id.home_title_bar_layout)
    FrameLayout homeTitleBarLayout;
    @BindView(R.id.home_title_bar_bg_view)
    View homeTitleBarBgView;
    @BindView(R.id.home_recyclerview)
    RecyclerView mRecyclerView;

    private HomeMultipleRecycleAdapter mAdapter;
    /**
     * 加载首页样式标记,可以切换样式，暂时没支持
     */
    private int flag = 1;
    private int distanceY;
    @Inject
    HomePresenter mPresenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
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
        DaggerHomeFragmentComponent.builder()
                .appComponent(getAppComponent())
                .homePresenterModule(new HomePresenterModule(this, MainDataManager.getInstance(mDataManager)))
                .build()
                .inject(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mRecyclerView.getContext(), 4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(DisplayUtils.dip2px(getContext(),3)));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                distanceY += dy;
                if (distanceY > DisplayUtils.dip2px(mActivity, 20)) {
                    homeTitleBarBgView.setBackgroundColor(getResources().getColor(R.color.white));
                    if (Build.VERSION.SDK_INT > 10) {
                        homeTitleBarBgView.setAlpha(distanceY * 1.0f / DisplayUtils.dip2px(mActivity, 100));
                    }
                    else {
                        DISTANCE_WHEN_TO_SELECTED = 20;
                    }
                }
                else {
                    homeTitleBarBgView.setBackgroundColor(0);
                }

                if (distanceY > DisplayUtils.dip2px(mActivity, DISTANCE_WHEN_TO_SELECTED) && !scanningLayout.isSelected()) {
                    scanningLayout.setSelected(true);
                    advisoryLayout.setSelected(true);
                }
                else if (distanceY <= DisplayUtils.dip2px(mActivity, DISTANCE_WHEN_TO_SELECTED) && scanningLayout.isSelected()) {
                    scanningLayout.setSelected(false);
                    advisoryLayout.setSelected(false);
                }
            }
        });
        mAdapter = new HomeMultipleRecycleAdapter(null);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setEnableLoadMore(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void requestData() {
        mPresenter.getHomeIndexData(flag);
//        flag = 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void setNormalView() {

    }

    @Override
    public void setNetErrorView() {

    }

    @Override
    public void reLogin() {

    }

    @Override
    public void setHomeIndexData(HomeIndex mBean) {
        mAdapter.getData().clear();
        mAdapter.resetMaxHasLoadPosition();
        mAdapter.setNewData(mBean.itemInfoList);
    }

    @Override
    public void setRecommendedWares(HomeIndex mBean) {
        mAdapter.getData().addAll(mBean.itemInfoList);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void setMoreRecommendedWares(HomeIndex mBean) {
        mAdapter.getData().addAll(mBean.itemInfoList);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAdapter.getData().size() >= 90){
                    mAdapter.loadMoreEnd(false);
                }
                else{
                    mPresenter.getRecommendedWares();
                }
            }
        },1000);
    }

    @OnClick({ R.id.scanning_layout, R.id.advisory_layout })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scanning_layout:
                toast("scanning_layout");
                break;
            case R.id.advisory_layout:
                toast("advisory_layout");
                break;
        }
    }
}
