package com.schauersberger.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.schauersberger.data.cache.NewsCache;
import com.schauersberger.data.entity.mapper.NewsEntityJsonMapper;
import com.schauersberger.data.net.RestApi;
import com.schauersberger.data.net.RestApiImpl;

import javax.inject.Inject;

public class NewsDataStoreFactory {
    private final Context context;
    private final NewsCache newsCache;

    @Inject
    NewsDataStoreFactory(@NonNull Context context, @NonNull NewsCache newsCache) {
        this.context = context.getApplicationContext();
        this.newsCache = newsCache;
    }

    /**
     * Create {@link NewsDataStore} from a news id.
     */
    public NewsDataStore create(String newsId) {
        NewsDataStore newsDataStore;

        if (!this.newsCache.isExpired() && this.newsCache.isCached(newsId)) {
            newsDataStore = new DiskNewsDataStore(this.newsCache);
        } else {
            newsDataStore = createCloudDataStore();
        }

        return newsDataStore;
    }

    /**
     * Create {@link NewsDataStore} to retrieve data from the Cloud.
     */
    public NewsDataStore createCloudDataStore() {
        final NewsEntityJsonMapper newsEntityJsonMapper = new NewsEntityJsonMapper();
        final RestApi restApi = new RestApiImpl(this.context, newsEntityJsonMapper);

        return new CloudNewsDataStore(restApi, this.newsCache);
    }
}

