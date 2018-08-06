package com.dhunter.android.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.dhunter.android.R;
import com.dhunter.android.entity.HomeIndex;
import com.dhunter.common.widget.bannerView.holder.MZViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by dhunter on 2018/8/2.
 */

public class BannerPaddingViewHolder implements MZViewHolder<HomeIndex.ItemInfoListBean.ItemContentListBean> {

    //    private TextView mTextView;
//    private TextView mTextView;
    private SimpleDraweeView mImageView;
    private LinearLayout mLinearlayout;


    @Override
    public View createView(Context context) {
        // 返回页面布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.homerecycle_middle_banner_content, null);
        mLinearlayout = (LinearLayout) view.findViewById(R.id.banner_ll);
        mImageView = (SimpleDraweeView) view.findViewById(R.id.sdv_item_fresco_content);
        return view;
    }

    @Override
    public void onBind(Context context, int position, final HomeIndex.ItemInfoListBean.ItemContentListBean data) {
        // 数据绑定
        mImageView.setImageURI(data.imageUrl);
//        mImageView.setImageResource(data.models.imgUrl);
//        mTextView.setText(data.title);

//        mLinearlayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), data.link, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}