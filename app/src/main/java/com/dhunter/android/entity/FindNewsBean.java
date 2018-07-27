package com.dhunter.android.entity;

import java.util.List;

/**
 * Created by dhunter on 2018/6/27.
 */

public class FindNewsBean {

    private int code;
    private String msg;
    private List<NewsItem> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewsItem> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsItem> newslist) {
        this.newslist = newslist;
    }
}
