package com.example.longyuan.hubtraffic.main;

import android.util.Log;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.network.api.PornhubAPI;
import com.example.longyuan.hubtraffic.pojo.video.VideosResponse;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    @Inject
    PornhubAPI mPornhubAPI;


    public MainPresenter(MainContract.View view) {

        mView = view;

        App.getAppComponent().inject(this);

        view.setPresnter(this);
    }

    @Override
    public void start() {

        loadVideos();
    }

    private void loadVideos() {

        Map<String, String> queries = new HashMap<>();
        queries.put("thumbsize ", "medium");


        mPornhubAPI.getVideos(queries)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // .map(data -> checkLatestPOstList(data))
                .subscribe(data ->  updateData(data),throwable -> Log.d("",throwable.getLocalizedMessage()));

    }

    private void updateData(VideosResponse videosResponse){
        mView.updateData(videosResponse.getVideos());
    }
}
