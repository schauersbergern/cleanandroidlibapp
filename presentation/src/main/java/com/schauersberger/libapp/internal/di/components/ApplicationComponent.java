package com.schauersberger.libapp.internal.di.components;

import android.content.Context;

import com.schauersberger.domain.executor.PostExecutionThread;
import com.schauersberger.domain.executor.ThreadExecutor;
import com.schauersberger.domain.repository.NewsRepository;
import com.schauersberger.libapp.internal.di.modules.ApplicationModule;
import com.schauersberger.libapp.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    NewsRepository newsRepository();
}
