package com.schauersberger.domain.interactor;

import com.schauersberger.domain.News;
import com.schauersberger.domain.executor.PostExecutionThread;
import com.schauersberger.domain.executor.ThreadExecutor;
import com.schauersberger.domain.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link News}.
 */
public class GetNewsList extends UseCase<List<News>, Void> {
    private final NewsRepository newsRepository;

    @Inject
    GetNewsList(NewsRepository newsRepository, ThreadExecutor threadExecutor,
                PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.newsRepository = newsRepository;
    }

    @Override Observable<List<News>> buildUseCaseObservable(Void unused) {
        return this.newsRepository.news();
    }
}

