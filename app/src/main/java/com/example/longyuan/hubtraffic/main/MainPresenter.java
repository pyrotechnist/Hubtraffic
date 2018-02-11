package com.example.longyuan.hubtraffic.main;

import android.util.Log;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.VideoRepository;
import com.example.longyuan.hubtraffic.network.api.PornhubAPI;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.pojo.video.VideosResponse;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public class MainPresenter implements MainContract.Presenter {

    private final static String TAG = MainPresenter.class.getSimpleName();

    private MainContract.View mView;

   /* @Inject
    PornhubAPI mPornhubAPI;*/

    @Inject
    protected VideoRepository mVideoRepository;



    public MainPresenter(MainContract.View view) {

        mView = view;

        App.getAppComponent().inject(this);

        view.setPresnter(this);
    }

    @Override
    public void start() {

        loadVideos();
    }

    @Override
    public void loadVideos() {

        Map<String, String> queries = new HashMap<>();
        queries.put("thumbsize ", "medium");


        mVideoRepository.loadVideos(new DataStore.LoadVideosCallback() {
            @Override
            public void onVideosLoaded(List<VideosItem> videosItems) {
                updateData(videosItems);
            }

            @Override
            public void onError(String error) {

                mView.errorToast(error);
            }
        });

      /*  mPornhubAPI.getVideos(queries)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // .map(data -> checkLatestPOstList(data))
                .subscribe(data ->  updateData(data),throwable -> Log.d("",throwable.getLocalizedMessage()));*/

    }

   @Override
    public void refreshVideos() {
       /* mVideoRepository.loadVideos(new DataStore.LoadVideosCallback() {
            @Override
            public void onVideosLoaded(List<VideosItem> videosItems) {
                updateData(videosItems);
            }
        },true);*/

    }



    private void updateData(List<VideosItem> videosItems){
        mView.updateData(videosItems);
    }


}
