package com.schauersberger.libapp.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.schauersberger.libapp.R;
import com.schauersberger.libapp.internal.di.components.NewsComponent;
import com.schauersberger.libapp.model.NewsModel;
import com.schauersberger.libapp.presenter.NewsListPresenter;
import com.schauersberger.libapp.view.NewsListView;
import com.schauersberger.libapp.view.adapter.NewsAdapter;
import com.schauersberger.libapp.view.adapter.NewsLayoutManager;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsListFragment extends BaseFragment implements NewsListView {

    /**
     * Interface for listening news list events.
     */
    public interface NewsListListener {
        //add eventlistener here
    }

    @Inject NewsListPresenter newsListPresenter;
    @Inject NewsAdapter newsAdapter;

    @Bind(R.id.rv_news) RecyclerView rv_news;
    @Bind(R.id.rl_progress) RelativeLayout rl_progress;
    @Bind(R.id.rl_retry) RelativeLayout rl_retry;
    @Bind(R.id.bt_retry) Button bt_retry;

    private NewsListListener newsListListener;

    public NewsListFragment() {
        setRetainInstance(true);
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof NewsListListener) {
            this.newsListListener = (NewsListListener) activity;
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(NewsComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.list_news_fragment, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.newsListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadNewsList();
        }
    }

    @Override public void onResume() {
        super.onResume();
        this.newsListPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.newsListPresenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        rv_news.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.newsListPresenter.destroy();
    }

    @Override public void onDetach() {
        super.onDetach();
        this.newsListListener = null;
    }

    @Override public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override public void renderNewsList(Collection<NewsModel> newsModelCollection) {
        if (newsModelCollection != null) {
            this.newsAdapter.setNewsCollection(newsModelCollection);
        }
    }

    @Override public void viewNews(NewsModel newsModel) {
        if (this.newsListListener != null) {
            //this.newsListListener.onNewsClicked(newsModel);
        }
    }

    @Override public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        //this.newsAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_news.setLayoutManager(new NewsLayoutManager(context()));
        this.rv_news.setAdapter(newsAdapter);
    }

    /**
     * Loads all newss.
     */
    private void loadNewsList() {
        this.newsListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry) void onButtonRetryClick() {
        NewsListFragment.this.loadNewsList();
    }

    /*
    private NewsAdapter.OnItemClickListener onItemClickListener =
            new NewsAdapter.OnItemClickListener() {
                @Override public void onNewsItemClicked(NewsModel newsModel) {
                    if (NewsListFragment.this.newsListPresenter != null && newsModel != null) {
                        NewsListFragment.this.newsListPresenter.onNewsClicked(newsModel);
                    }
                }
            };*/
}