package com.schauersberger.data.entity.mapper;

import com.schauersberger.data.entity.NewsEntity;
import com.schauersberger.domain.News;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link NewsEntity} (in the data layer) to {@link News} in the
 * domain layer.
 */
@Singleton
public class NewsEntityDataMapper {
    @Inject
    NewsEntityDataMapper() {}

    /**
     * Transform a {@link NewsEntity} into an {@link News}.
     *
     * @param newsEntity Object to be transformed.
     * @return {@link News} if valid {@link NewsEntity} otherwise null.
     */
    public News transform(NewsEntity newsEntity) {
        News news = null;
        if (newsEntity != null) {
            news = new News(newsEntity.getNewsId());
            news.setHeadline(newsEntity.getHeadline());
            news.setPicUrl(newsEntity.getPicUrl());
        }
        return news;
    }

    /**
     * Transform a List of {@link NewsEntity} into a Collection of {@link News}.
     *
     * @param newsEntityCollection Object Collection to be transformed.
     * @return {@link News} if valid {@link NewsEntity} otherwise null.
     */
    public List<News> transform(Collection<NewsEntity> newsEntityCollection) {
        final List<News> newsList = new ArrayList<>(20);
        for (NewsEntity newsEntity : newsEntityCollection) {
            final News news = transform(newsEntity);
            if (news != null) {
                newsList.add(news);
            }
        }
        return newsList;
    }
}

