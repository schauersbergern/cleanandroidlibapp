package com.schauersberger.libapp.mapper;

import com.schauersberger.domain.News;
import com.schauersberger.libapp.internal.di.PerActivity;
import com.schauersberger.libapp.model.NewsModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link News} (in the domain layer) to {@link NewsModel} in the
 * presentation layer.
 */
@PerActivity
public class NewsModelDataMapper {

    @Inject
    public NewsModelDataMapper() {}

    /**
     * Transform a {@link News} into an {@link NewsModel}.
     *
     * @param news Object to be transformed.
     * @return {@link NewsModel}.
     */
    public NewsModel transform(News news) {
        if (news == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final NewsModel newsModel = new NewsModel(news.getNewsId());
        newsModel.setHeadline(news.getHeadline());
        newsModel.setPicUrl(news.getPicUrl());

        return newsModel;
    }

    /**
     * Transform a Collection of {@link News} into a Collection of {@link NewsModel}.
     *
     * @param newssCollection Objects to be transformed.
     * @return List of {@link NewsModel}.
     */
    public Collection<NewsModel> transform(Collection<News> newssCollection) {
        Collection<NewsModel> newsModelsCollection;

        if (newssCollection != null && !newssCollection.isEmpty()) {
            newsModelsCollection = new ArrayList<>();
            for (News news : newssCollection) {
                newsModelsCollection.add(transform(news));
            }
        } else {
            newsModelsCollection = Collections.emptyList();
        }

        return newsModelsCollection;
    }

}
