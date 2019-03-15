package com.schauersberger.libapp.presenter;

import android.support.annotation.NonNull;

import com.schauersberger.domain.News;
import com.schauersberger.domain.exception.DefaultErrorBundle;
import com.schauersberger.domain.exception.ErrorBundle;
import com.schauersberger.domain.interactor.DefaultObserver;
import com.schauersberger.domain.interactor.GetNewsList;
import com.schauersberger.libapp.exception.ErrorMessageFactory;
import com.schauersberger.libapp.mapper.NewsModelDataMapper;
import com.schauersberger.libapp.model.NewsModel;
import com.schauersberger.libapp.view.NewsListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class NewsListPresenter implements Presenter {

    private NewsListView newsListView;

    private final GetNewsList getNewsListUseCase;
    private final NewsModelDataMapper newsModelDataMapper;

    @Inject
    public NewsListPresenter(GetNewsList getNewsListUserCase, NewsModelDataMapper newsModelDataMapper) {
        this.getNewsListUseCase = getNewsListUserCase;
        this.newsModelDataMapper = newsModelDataMapper;
    }

    public void setView(@NonNull NewsListView view) {
        this.newsListView = view;
    }

    @Override public void resume() {}

    @Override public void pause() {}

    @Override public void destroy() {
        this.getNewsListUseCase.dispose();
        this.newsListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the news list.
     */
    public void initialize() {
        this.loadNewsList();
    }

    /**
     * Loads all users.
     */
    private void loadNewsList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getNewsList();
    }



    private void showViewLoading() {
        this.newsListView.showLoading();
    }

    private void hideViewLoading() {
        this.newsListView.hideLoading();
    }

    private void showViewRetry() {
        this.newsListView.showRetry();
    }

    private void hideViewRetry() {
        this.newsListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.newsListView.context(), errorBundle.getException());
        this.newsListView.showError(errorMessage);
    }

    private void showNewsCollectionInView(Collection<News> newsCollection) {
        final Collection<NewsModel> newsModelsCollection = this.newsModelDataMapper.transform(newsCollection);
        this.newsListView.renderNewsList(newsModelsCollection);
    }

    private void getNewsList() {
        this.getNewsListUseCase.execute(new UserListObserver(), null);
    }

    private final class UserListObserver extends DefaultObserver<List<News>> {

        @Override public void onComplete() {
            NewsListPresenter.this.hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            NewsListPresenter.this.hideViewLoading();
            NewsListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            NewsListPresenter.this.showViewRetry();
        }

        @Override public void onNext(List<News> news) {
            NewsListPresenter.this.showNewsCollectionInView(news);
        }
    }
}
