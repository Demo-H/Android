package com.dhunter.android.adapter;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhunter.android.R;
import com.dhunter.android.entity.HomeIndex;
import com.dhunter.common.recycleview.BaseQuickAdapter;
import com.dhunter.common.recycleview.BaseViewHolder;
import com.dhunter.common.utils.DisplayUtils;
import com.dhunter.common.view.ExpandImageView;

import java.util.List;

/**
 * Created by dhunter on 2018/8/2.
 */

public class SpikeContentAdapter extends BaseQuickAdapter<HomeIndex.ItemInfoListBean.ItemContentListBean, BaseViewHolder> {
    public SpikeContentAdapter(int layoutResId, List<HomeIndex.ItemInfoListBean.ItemContentListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeIndex.ItemInfoListBean.ItemContentListBean item) {
        ((ExpandImageView) helper.getView(R.id.spike_ware_img)).setImageURI(item.imageUrl);
        helper.setText(R.id.spike_ware_now_price,item.itemTitle);
        helper.setText(R.id.spike_ware_old_price,item.itemSubTitle);
        if (!TextUtils.isEmpty(item.itemSubscript)){
            helper.getView(R.id.spike_ware_subscript).setVisibility(View.VISIBLE);
            helper.setText(R.id.spike_ware_subscript,item.itemSubscript);
        }
        ((TextView)helper.getView(R.id.spike_ware_old_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.homerecycle_spike_content,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (0.286 * DisplayUtils.getScreenWidthPixels(mContext)), LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return view;

    }
}
