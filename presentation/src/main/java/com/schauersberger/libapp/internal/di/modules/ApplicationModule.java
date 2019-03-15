package com.schauersberger.libapp.internal.di.modules;

import android.content.Context;

import com.schauersberger.data.cache.NewsCache;
import com.schauersberger.data.cache.NewsCacheImpl;
import com.schauersberger.data.executor.JobExecutor;
import com.schauersberger.data.repository.NewsDataRepository;
import com.schauersberger.domain.executor.PostExecutionThread;
import com.schauersberger.domain.executor.ThreadExecutor;
import com.schauersberger.domain.repository.NewsRepository;
import com.schauersberger.libapp.AndroidApplication;
import com.schauersberger.libapp.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    NewsCache provideNewsCache(NewsCacheImpl newsCache) {
        return newsCache;
    }

    @Provides @Singleton
    NewsRepository provideNewsRepository(NewsDataRepository newsDataRepository) {
        return newsDataRepository;
    }
}
