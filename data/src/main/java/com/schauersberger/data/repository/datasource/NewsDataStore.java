package com.schauersberger.data.repository.datasource;

import com.schauersberger.data.entity.NewsEntity;

import java.util.List;

import io.reactivex.Observable;

public interface NewsDataStore {

    /**
     * Get an {@link Observable} which will emit a List of {@link NewsEntity}.
     */
    Observable<List<NewsEntity>> newsEntityList();

    /**
     * Get an {@link Observable} which will emit a {@link NewsEntity} by its id.
     *
     * @param newsId The id to retrieve news data.
     */
    //Observable<NewsEntity> newsEntityDetails(final String newsId);
}

