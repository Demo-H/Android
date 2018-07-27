package com.dhunter.common.config;

/**
 * Created by dhunter on 2018/6/22.
 */

public class GlobalConfig {

    /** Common **/
    public static final boolean DEBUG = true;

    /** permission **/
    public static final String PACKAGE_URL_SCHEME = "package_url_scheme";

    /** loadingdialog **/
    public static final String LOADING_TIP = "loading_tip";

    public static String SHARE_PREFERENCE_FILE_NAME = "coll";

    /**network **/
    public static int HTTP_READ_TIME_OUT = 15;
    public static int HTTP_CONNECT_TIME_OUT = 15;
    public static String BASE_URL = "http://api.tianapi.com/";
}
