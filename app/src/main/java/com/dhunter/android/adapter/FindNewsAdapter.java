package com.dhunter.android.adapter;

import com.dhunter.android.R;
import com.dhunter.android.entity.NewsItem;
import com.dhunter.common.recycleview.BaseQuickAdapter;
import com.dhunter.common.recycleview.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by dhunter on 2018/6/27.
 */

public class FindNewsAdapter extends BaseQuickAdapter<NewsItem, BaseViewHolder> {

    public FindNewsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsItem item) {
        helper.setText(R.id.weChatTitleTv, item.getTitle());
        helper.setText(R.id.weChatNameTv, item.getDescription());
        helper.setText(R.id.weChatTimeTv, item.getCtime());
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.weChatIv);
        simpleDraweeView.setImageURI(item.getPicUrl());

    }
}
