package com.schauersberger.data.repository.datasource;

import com.schauersberger.data.cache.NewsCache;
import com.schauersberger.data.entity.NewsEntity;
import com.schauersberger.data.net.RestApi;

import java.util.List;

import io.reactivex.Observable;

public class CloudNewsDataStore implements NewsDataStore {

    private final RestApi restApi;
    private final NewsCache newsCache;

    /**
     * Construct a {@link NewsDataStore} based on connections to the api (Cloud).
     *
     * @param restApi The {@link RestApi} implementation to use.
     * @param newsCache A {@link NewsCache} to cache data retrieved from the api.
     */
    CloudNewsDataStore(RestApi restApi, NewsCache newsCache) {
        this.restApi = restApi;
        this.newsCache = newsCache;
    }

    @Override public Observable<List<NewsEntity>> newsEntityList() {
        return this.restApi.newsEntityList();
    }

    /*
    @Override public Observable<NewsEntity> newsEntityDetails(final int newsId) {
        return this.restApi.newsEntityById(userId).doOnNext(CloudNewsDataStore.this.newsCache::put);
    }
    */
}
