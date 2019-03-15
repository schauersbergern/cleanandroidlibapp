package com.schauersberger.libapp.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.schauersberger.libapp.R;
import com.schauersberger.libapp.model.NewsModel;
import com.schauersberger.libapp.view.component.AutoLoadImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsModel> newsCollection;
    private final LayoutInflater layoutInflater;

    @Inject
    NewsAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.newsCollection = Collections.emptyList();
    }

    @Override public int getItemCount() {
        return (this.newsCollection != null) ? this.newsCollection.size() : 0;
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final NewsModel newsModel = this.newsCollection.get(position);

        holder.textViewTitle.setText(newsModel.getHeadline());

        DownloadImageTask dit = new DownloadImageTask(holder.image);
        dit.execute(newsModel.getPicUrl());

    }


    @Override public long getItemId(int position) {
        return position;
    }

    public void setNewsCollection(Collection<NewsModel> newsCollection) {
        this.validateNewsCollection(newsCollection);
        this.newsCollection = (List<NewsModel>) newsCollection;
        this.notifyDataSetChanged();
    }

    private void validateNewsCollection(Collection<NewsModel> usersCollection) {
        if (usersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title) TextView textViewTitle;
        @Bind(R.id.pic) ImageView image;

        NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            try {
                String src = urls[0];
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute(Bitmap bmp) {

            if (bmp != null) {
                imageView.setImageBitmap(bmp);
            }

        }
    }

}
