package com.schauersberger.data.repository;

import com.schauersberger.data.entity.mapper.NewsEntityDataMapper;
import com.schauersberger.data.repository.datasource.NewsDataStore;
import com.schauersberger.data.repository.datasource.NewsDataStoreFactory;
import com.schauersberger.domain.News;
import com.schauersberger.domain.repository.NewsRepository;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NewsDataRepository implements NewsRepository {

    private final NewsDataStoreFactory newsDataStoreFactory;
    private final NewsEntityDataMapper newsEntityDataMapper;

    /**
     * Constructs a {@link NewsRepository}.
     *
     * @param newsDataStoreFactory A factory to construct different data source implementations.
     * @param newsEntityDataMapper {@link NewsEntityDataMapper}.
     */
    @Inject
    NewsDataRepository(NewsDataStoreFactory newsDataStoreFactory, NewsEntityDataMapper newsEntityDataMapper) {
        this.newsDataStoreFactory = newsDataStoreFactory;
        this.newsEntityDataMapper = newsEntityDataMapper;
    }


    @Override
    public Observable<List<News>> news() {
        final NewsDataStore newsDataStore = this.newsDataStoreFactory.createCloudDataStore();
        return newsDataStore.newsEntityList().map(this.newsEntityDataMapper::transform);
    }

    /*
    @Override
    public Observable<News> news(int newsId) {
        return null;
    }
    */
}
