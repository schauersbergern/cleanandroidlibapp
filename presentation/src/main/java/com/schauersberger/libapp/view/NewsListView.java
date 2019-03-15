package com.schauersberger.libapp.view;

import com.schauersberger.libapp.model.NewsModel;

import java.util.Collection;

public interface NewsListView extends LoadDataView {
    /**
     * Render a news list in the UI.
     *
     * @param newsModelCollection The collection of {@link NewsModel} that will be shown.
     */
    void renderNewsList(Collection<NewsModel> newsModelCollection);

    /**
     * View a {@link NewsModel} profile/details.
     *
     * @param newsModel The news that will be shown.
     */
    void viewNews(NewsModel newsModel);
}
