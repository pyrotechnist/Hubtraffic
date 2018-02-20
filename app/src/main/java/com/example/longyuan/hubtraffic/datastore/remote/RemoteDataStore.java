package com.example.longyuan.hubtraffic.datastore.remote;

import android.util.Log;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.network.api.PornhubAPI;
import com.example.longyuan.hubtraffic.pojo.star.StarsItem;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.pojo.video.VideosResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LONGYUAN on 2018/2/10.
 */

public class RemoteDataStore implements DataStore {

    private final static String TAG = RemoteDataStore.class.getSimpleName();

    private List<StarsItem> mStarsItems;

    @Inject
    PornhubAPI mPornhubAPI;


    public RemoteDataStore() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void loadVideos(LoadVideosCallback loadVideosCallback) {

        loadVideos(loadVideosCallback,null);

    }

    public void loadVideos(LoadVideosCallback loadVideosCallback,Map<String, String> queries) {


        if(queries == null){
            queries = new HashMap<>();
            queries.put("thumbsize", "medium");
            queries.put("search", "tits");
            int random = new Random().nextInt(30);

            queries.put("page", String.valueOf(random));
        }
        queries.put("thumbsize", "medium");

        mPornhubAPI.getVideos(queries)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // .map(data -> checkLatestPOstList(data))
                .subscribe(data ->  loadVideosCallback.onVideosLoaded(data.getVideos()),throwable -> loadVideosCallback.onError(throwable.getLocalizedMessage()));

    }



    private void updateData(VideosResponse videosResponse){
        //mView.updateData(videosResponse.getVideos());
    }


    public void loadStars (LoadStarsCallback loadStarsCallback) {


        if(mStarsItems == null)
        {
            mStarsItems = new ArrayList<>();
        }
        else
        {
            loadStarsCallback.onStarsLoaded(mStarsItems);
        }

        mPornhubAPI.getStarsDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(starsDetailResponse -> Observable.from(starsDetailResponse.getStars()))
                .filter(starsItem -> !starsItem.getStar().getStarThumb().equals(""))
                .filter(starsItem -> !starsItem.getStar().getGender().equals("male"))
                .filter(starsItem -> !starsItem.getStar().getStarThumb().contains("pornstars/default"))
                .filter(starsItem -> Integer.parseInt(starsItem.getStar().getVideosCount()) >500)
                .subscribe(starsItem -> mStarsItems.add(starsItem),throwable -> loadStarsCallback.onError(throwable.getLocalizedMessage()),() -> loadStarsCallback.onStarsLoaded(mStarsItems) );
                // .map(data -> checkLatestPOstList(data))

      //  loadStarsCallback.onStarsLoaded(mStarsItems);
    }


}
