package com.schauersberger.data.entity;

import com.google.gson.annotations.SerializedName;

public class NewsEntity {

    @SerializedName("id")
    private int newsId;
    @SerializedName("title")
    private String headline;
    @SerializedName("modified")
    private String picUrl;

    public NewsEntity() {}

    public String getNewsId() {
        return String.valueOf(newsId);
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
