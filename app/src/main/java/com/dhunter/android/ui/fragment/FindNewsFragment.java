package com.dhunter.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhunter.android.R;
import com.dhunter.android.adapter.FindNewsAdapter;
import com.dhunter.android.base.BaseFragment;
import com.dhunter.android.config.Constant;
import com.dhunter.android.entity.FindNewsBean;
import com.dhunter.android.http.NewsDataManager;
import com.dhunter.android.ui.component.DaggerFindNewsFragmentComponent;
import com.dhunter.android.ui.contract.FindNewsContract;
import com.dhunter.android.ui.module.FindNewsPresenterModule;
import com.dhunter.android.ui.presenter.FindNewsPresenter;
import com.dhunter.common.base.StateView;
import com.dhunter.common.recycleview.BaseQuickAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dhunter on 2018/6/27.
 */

public class FindNewsFragment extends BaseFragment implements FindNewsContract.View,
        BaseQuickAdapter.RequestLoadMoreListener {

//    @BindView(R.id.refreshRv)
//    PullToRefreshRecyclerView refreshRv;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.state_view)
    StateView stateView;

    private View rootView = null;
    private int mTabPosition;
    private FindNewsAdapter mAdapter;

    @Inject
    FindNewsPresenter mPresenter;

    public static FindNewsFragment newInstance(Bundle bundle) {
        FindNewsFragment fragment = new FindNewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTabPosition = bundle.getInt(Constant.FRAGMENT_TAB_POSITION);
        }
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
        DaggerFindNewsFragmentComponent.builder()
                .appComponent(getAppComponent())
                .findNewsPresenterModule(new FindNewsPresenterModule(this, NewsDataManager.getInstance(mDataManager)))
                .build()
                .inject(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

//        refreshRv.setLayoutManager(linearLayoutManager);
//        refreshRv.setPullRefreshEnabled(true);
//        refreshRv.setLoadMoreEnabled(true);
//        refreshRv.setRefreshAndLoadMoreListener(this);
        mAdapter = new FindNewsAdapter(R.layout.item_findnews_recycleview);
        mRecyclerView.setAdapter(mAdapter);
//        refreshRv.setAdapter(mAdapter);
        stateView.showViewByState(StateView.STATE_LOADING);
        stateView.setOnDisConnectViewListener(new StateView.OnDisConnectListener() {
            @Override
            public void onDisConnectViewClick() {
                requestData();
            }
        });

    }

    @Override
    public void requestData() {
        mPresenter.getNewsData(mTabPosition);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_news;
    }

    @Override
    public void setNewsData(FindNewsBean mBean) {
        Log.i("a", "aa");
//        if(refreshRv.isRefreshing()) {
//            refreshRv.refreshComplete();
//        } else if(refreshRv.isLoading()) {
//            refreshRv.loadMoreComplete();
//        }
        if(mBean.getNewslist().size()==0) {
            stateView.showViewByState(StateView.STATE_NO_DATA);
        } else {
            stateView.setVisibility(View.GONE);
            mAdapter.setNewData(mBean.getNewslist());
        }
    }

    @Override
    public void setMoreNewsData(FindNewsBean mBean) {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if(refreshRv != null){
//            refreshRv.destroy();
//            refreshRv = null;
//        }
    }
}
