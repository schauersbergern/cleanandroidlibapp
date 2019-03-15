package com.schauersberger.libapp.view.activity;

import android.os.Bundle;

import com.schauersberger.libapp.R;
import com.schauersberger.libapp.internal.di.HasComponent;
import com.schauersberger.libapp.internal.di.components.DaggerNewsComponent;
import com.schauersberger.libapp.internal.di.components.NewsComponent;
import com.schauersberger.libapp.view.fragment.NewsListFragment;

public class NewsListActivity extends BaseActivity implements HasComponent<NewsComponent>, NewsListFragment.NewsListListener {

    private NewsComponent newsComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new NewsListFragment());
        }
    }

    private void initializeInjector() {
        this.newsComponent = DaggerNewsComponent.builder().applicationComponent(getApplicationComponent()).activityModule(getActivityModule()).build();
    }

    @Override
    public NewsComponent getComponent() {
        return newsComponent;
    }
}
