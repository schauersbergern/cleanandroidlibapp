package com.schauersberger.domain;

public class News {

    private final String newsId;

    public News(String newsId) {
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
