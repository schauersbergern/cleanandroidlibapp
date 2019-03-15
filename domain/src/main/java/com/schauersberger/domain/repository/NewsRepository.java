package com.schauersberger.domain.repository;

import com.schauersberger.domain.News;
import java.util.List;
import io.reactivex.Observable;

public interface NewsRepository {

    /**
     * Get an {@link Observable} which will emit a List of {@link News}.
     */
    Observable<List<News>> news();

    /**
     * Get an {@link Observable} which will emit a {@link News}.
     *
     * @param newsId The news id used to retrieve news data.
     */
    //Observable<News> news(final int newsId);
}
