package com.dhunter.android.entity;

import com.dhunter.android.config.Constant;
import com.dhunter.common.recycleview.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by dhunter on 2018/6/27.
 */

public class HomeIndex {


    /**
     * json可以参考home.txt文件
     */

    public String code;
    public List<ItemInfoListBean> itemInfoList;

    public static class ItemInfoListBean implements MultiItemEntity {

        public String itemType;
        public String module;
        public List<ItemContentListBean> itemContentList;

        @Override
        public int getItemType() {
            if("topBanner".equals(itemType)){
                return Constant.TYPE_TOP_BANNER;
            }
            else if("iconList".equals(itemType)){
                return Constant.TYPE_ICON_LIST;
            }
            else if("newUser".equals(itemType)){
                return Constant.TYPE_NEW_USER;
            }
            else if("jdBulletin".equals(itemType)){
                return Constant.TYPE_JD_BULLETIN;
            }
            else if("jdSpikeHeader".equals(itemType)){
                return Constant.TYPE_JD_SPIKE_HEADER;
            }
            else if("jdSpikeContent".equals(itemType)){
                return Constant.TYPE_JD_SPIKE_CONTENT;
            }
            else if("showEvent".equals(itemType)){
                return Constant.TYPE_SHOW_EVENT_3;
            }
            else if("findGoodStuff".equals(itemType)){
                return Constant.TYPE_FIND_GOOD_STUFF;
            }
            else if("type_211".equals(itemType)){
                return Constant.TYPE_WIDTH_PROPORTION_211;
            }
            else if("type_Title".equals(itemType)){
                return Constant.TYPE_TITLE;
            }
            else if("type_22".equals(itemType)){
                return Constant.TYPE_WIDTH_PROPORTION_22;
            }
            else if("type_1111".equals(itemType)){
                return Constant.TYPE_WIDTH_PROPORTION_1111;
            }
            else if("type_middleBanner".equals(itemType)){
                return Constant.TYPE_MIDDLE_BANNER;
            }
            else if("showEventFillUp".equals(itemType)){
                return Constant.TYPE_SHOW_EVENT_FILL_UP;
            }
            else if("findGoodShop".equals(itemType)){
                return Constant.TYPE_FIND_GOOD_SHOP;
            }
            else if("preferredList".equals(itemType)){
                return Constant.TYPE_PREFERRED_LIST;
            }
            else if("live".equals(itemType)){
                return Constant.TYPE_LIVE;
            }
            else if("recommended_ware".equals(itemType)){
                return Constant.TYPE_RECOMMENDED_WARE;
            }
            return Constant.TYPE_TITLE;
        }

        public int getSpanSize() {

            if("recommended_ware".equals(itemType)){
                return 2;
            }
            return 4;
        }

        public static class ItemContentListBean {
            /**
             * imageUrl : https://m.360buyimg.com/mobilecms/s720x322_jfs/t4903/41/12296166/85214/15205dd6/58d92373N127109d8.jpg!q70.jpg
             * clickUrl : 男装超级品牌类日
             */

            public String imageUrl;
            public String clickUrl;
            public String itemTitle;
            public String itemSubTitle;
            public String itemSubscript;
            public String itemRecommendedLanguage;
            public String itemBackgroundImageUrl;

        }
    }
}
