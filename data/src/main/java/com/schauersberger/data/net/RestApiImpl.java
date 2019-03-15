package com.schauersberger.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.schauersberger.data.entity.NewsEntity;
import com.schauersberger.data.entity.mapper.NewsEntityJsonMapper;
import com.schauersberger.data.exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;

public class RestApiImpl implements RestApi {

    private final Context context;
    private final NewsEntityJsonMapper newsEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context {@link android.content.Context}.
     * @param newsEntityJsonMapper {@link NewsEntityJsonMapper}.
     */
    public RestApiImpl(Context context, NewsEntityJsonMapper newsEntityJsonMapper) {
        if (context == null || newsEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.newsEntityJsonMapper = newsEntityJsonMapper;
    }

    @Override public Observable<List<NewsEntity>> newsEntityList() {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseNewsEntities = getNewsEntitiesFromApi();
                    if (responseNewsEntities != null) {
                        emitter.onNext(newsEntityJsonMapper.transformNewsEntityCollection(responseNewsEntities));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }

    private String getNewsEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(NEWS_URL).requestSyncCall();
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
