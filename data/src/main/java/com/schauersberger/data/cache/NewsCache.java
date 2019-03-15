package com.schauersberger.data.cache;

import com.schauersberger.data.entity.NewsEntity;

import io.reactivex.Observable;

public interface NewsCache {
    /**
     * Gets an {@link Observable} which will emit a {@link NewsEntity}.
     *
     * @param newsId The news id to retrieve data.
     */
    Observable<NewsEntity> get(final String newsId);

    /**
     * Puts and element into the cache.
     *
     * @param newsEntity Element to insert in the cache.
     */
    void put(NewsEntity newsEntity);

    /**
     * Checks if an element (News) exists in the cache.
     *
     * @param newsId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final String newsId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}

