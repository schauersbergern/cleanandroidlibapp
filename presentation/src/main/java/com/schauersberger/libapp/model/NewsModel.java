package com.schauersberger.libapp.model;

public class NewsModel {
    private final String newsId;

    public NewsModel(String newsId) {
        this.newsId = newsId;
    }

    private String headline;
    private String picUrl;

    public String getNewsId() {
        return newsId;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
