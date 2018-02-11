package com.example.longyuan.hubtraffic.datastore.remote;

import android.util.Log;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.network.api.PornhubAPI;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.pojo.video.VideosResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LONGYUAN on 2018/2/10.
 */

public class RemoteDataStore implements DataStore {

    private final static String TAG = RemoteDataStore.class.getSimpleName();

    @Inject
    PornhubAPI mPornhubAPI;


    public RemoteDataStore() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void loadVideos(LoadVideosCallback loadVideosCallback) {

        Map<String, String> queries = new HashMap<>();
        queries.put("thumbsize ", "medium");

        mPornhubAPI.getVideos(queries)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // .map(data -> checkLatestPOstList(data))
                .subscribe(data ->  loadVideosCallback.onVideosLoaded(data.getVideos()),throwable -> loadVideosCallback.onError(throwable.getLocalizedMessage()));

    }

    private void updateData(VideosResponse videosResponse){
        //mView.updateData(videosResponse.getVideos());
    }


}
