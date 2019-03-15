package com.schauersberger.data.repository.datasource;

import com.schauersberger.data.cache.NewsCache;
import com.schauersberger.data.entity.NewsEntity;

import java.util.List;

import io.reactivex.Observable;

public class DiskNewsDataStore implements NewsDataStore {

    private final NewsCache newsCache;

    /**
     * Construct a {@link NewsDataStore} based file system data store.
     *
     * @param newsCache A {@link NewsCache} to cache data retrieved from the api.
     */
    DiskNewsDataStore(NewsCache newsCache) {
        this.newsCache = newsCache;
    }

    @Override public Observable<List<NewsEntity>> newsEntityList() {
        //TODO: implement simple cache for storing/retrieving collections of news.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    /*
    @Override public Observable<NewsEntity> newsEntityDetails(final String newsId) {
        return this.newsCache.get(newsId);
    }*/
}
