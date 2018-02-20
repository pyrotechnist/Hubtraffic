package com.example.longyuan.hubtraffic.main;

import android.util.Log;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.VideoRepository;
import com.example.longyuan.hubtraffic.network.api.PornhubAPI;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.pojo.video.VideosResponse;
import com.example.longyuan.hubtraffic.util.enums.SearchOrdering;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.longyuan.hubtraffic.util.enums.SearchOrdering.FEATURED;
import static com.example.longyuan.hubtraffic.util.enums.SearchOrdering.MOSTVIEWED;
import static com.example.longyuan.hubtraffic.util.enums.SearchOrdering.NEWEST;
import static com.example.longyuan.hubtraffic.util.enums.SearchOrdering.RATING;

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

        view.setPresenter(this);
    }

    @Override
    public void start(String tag,String category, String starName) {

        loadVideos(tag,category,starName);
    }

    public void loadVideos(String tag,String category, String starName) {

        if(isStringEmpty(tag)&&isStringEmpty(category)&&isStringEmpty(starName))
        {
            loadVideos();
        }else
        {
            Map<String, String> queries = new HashMap<>();

            if(!isStringEmpty(tag))
            {
                queries.put("tags[]", tag);
            }

            if(!isStringEmpty(category))
            {
                queries.put("category", category);
            }

            if(!isStringEmpty(starName))
            {
                queries.put("stars", starName);
            }

            refreshVideos(queries);
        }

    }

    private static boolean isStringEmpty(String string){
        return string == null || string.equals("");
    }

    @Override
    public void loadVideos() {

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

    }

    public void loadVideos(Map<String,String> map) {
        Map<String, String> queries = new HashMap<>();
        queries.put("thumbsize ", "medium");



    }

   @Override
    public void refreshVideos(Map<String,String> map) {

       Map<String, String> queries;
        if(map == null)
        {
            queries = new HashMap<>();
            queries.put("search", "chinese");
            int random = new Random().nextInt(30);

            queries.put("page", String.valueOf(random));
        }else
        {
            queries =map;
        }

       queries.put("thumbsize", "medium");


       mVideoRepository.loadVideos(new DataStore.LoadVideosCallback() {
           @Override
           public void onVideosLoaded(List<VideosItem> videosItems) {
               updateData(videosItems);
           }

           @Override
           public void onError(String error) {

               mView.errorToast(error);
           }
       },queries);

    }


    @Override
    public void loadVideos(SearchOrdering searchOrdering) {

        @SearchOrdering.Ordering int today = searchOrdering.getOrdering();

        Map<String, String> queries = new HashMap<>();

        switch (today){
            case FEATURED:
                queries.put("ordering", "featured");
                break;
            case NEWEST:
                queries.put("ordering", "newest");
                break;
            case MOSTVIEWED:
                queries.put("ordering", "mostviewed");
                break;
            case RATING:
                queries.put("ordering", "rating");
                break;
            default:
                break;
        }

        mVideoRepository.loadVideos(new DataStore.LoadVideosCallback() {
            @Override
            public void onVideosLoaded(List<VideosItem> videosItems) {
                updateData(videosItems);
            }

            @Override
            public void onError(String error) {

                mView.errorToast(error);
            }
        },queries);
    }

    private void updateData(List<VideosItem> videosItems){
        mView.updateData(videosItems);
    }


}
