package com.schauersberger.libapp.internal.di.components;

import com.schauersberger.libapp.internal.di.PerActivity;
import com.schauersberger.libapp.internal.di.modules.ActivityModule;
import com.schauersberger.libapp.internal.di.modules.NewsModule;
import com.schauersberger.libapp.view.fragment.NewsListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, NewsModule.class})
public interface NewsComponent extends ActivityComponent {
    void inject(NewsListFragment listNewsFragment);
}