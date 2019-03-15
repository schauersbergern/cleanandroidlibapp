package com.schauersberger.data.net;

import com.schauersberger.data.entity.NewsEntity;

import java.util.List;

import io.reactivex.Observable;

public interface RestApi {
    String NEWS_URL = "https://api.russmedia.com/1.1/at/volat/listview/dashboard";


    /**
     * Retrieves an {@link Observable} which will emit a List of {@link NewsEntity}.
     */
    Observable<List<NewsEntity>> newsEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link NewsEntity}.
     *
     * @param newsId The news id used to get news data.
     *               Observable<NewsEntity> newsEntityById(final String newsId);
     */

}
