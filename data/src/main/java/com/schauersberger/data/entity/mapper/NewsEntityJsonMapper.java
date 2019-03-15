package com.schauersberger.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.schauersberger.data.entity.NewsEntity;
import com.schauersberger.data.helper.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsEntityJsonMapper {

    private final Gson gson;

    @Inject
    public NewsEntityJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string to {@link NewsEntity}.
     *
     * @param newsJsonResponse A json representing a news entry.
     * @return {@link NewsEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public NewsEntity transformNewsEntity(String newsJsonResponse) throws JsonSyntaxException {
        final Type newsEntityType = new TypeToken<NewsEntity>() {}.getType();
        return this.gson.fromJson(newsJsonResponse, newsEntityType);
    }

    /**
     * Transform from valid json string to List of {@link NewsEntity}.
     *
     * @param newsListJsonResponse A json representing a collection of news.
     * @return List of {@link NewsEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<NewsEntity> transformNewsEntityCollection(String newsListJsonResponse) throws JsonSyntaxException {
        /*
        final Type listOfnewsEntityType = new TypeToken<List<NewsEntity>>() {}.getType();
        return this.gson.fromJson(newsListJsonResponse, listOfnewsEntityType);
        */

        List<NewsEntity> newsList = new ArrayList<>(0);

        JSONObject newsObjects = JsonParser.JSONObject(newsListJsonResponse);

        JSONArray articles = JsonParser.getJSONArray(newsObjects, "elements");

        for (int i = 0; i < articles.length(); i++) {

            JSONObject newsObject = JsonParser.getJSONObject(articles, i);

            String title = JsonParser.getString(newsObject, "title");
            JSONObject attachment = JsonParser.getJSONObject(newsObject, "attachment");
            String picurl = JsonParser.getString(attachment, "url");

            if (title != null && picurl != null) {
                NewsEntity entity = new NewsEntity();
                entity.setHeadline(title);
                entity.setPicUrl("https://www.vol.at" + picurl);

                newsList.add(entity);
            }
        }

        return newsList;
    }
}
