package com.dhunter.android.adapter;

import com.dhunter.android.R;
import com.dhunter.common.recycleview.BaseQuickAdapter;
import com.dhunter.common.recycleview.BaseViewHolder;

/**
 * Created by dhunter on 2018/8/3.
 */

public class TypeOfGoodsNameAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public TypeOfGoodsNameAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.goods_type_name,item);
    }
}
