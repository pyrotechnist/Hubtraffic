package com.example.longyuan.hubtraffic.datastore;

import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.local.LocalDataStore;
import com.example.longyuan.hubtraffic.datastore.remote.RemoteDataStore;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.repository.IVideoRepository;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by LONGYUAN on 2018/2/10.
 */

public class VideoRepository implements DataStore {

    private final static String TAG = VideoRepository.class.getSimpleName();

    private LocalDataStore mLocalDataStore;
    private RemoteDataStore mRemoteDataStore;

   // private List<VideosItem> mVideosItemListCache;

    private ArrayMap<String,VideosItem> mVideosItemListCache;


    private LoadVideosCallback mLoadVideosCallback;

    @Inject
    public VideoRepository(RemoteDataStore remoteDataStore, LocalDataStore localDataStore){
        this.mLocalDataStore = localDataStore;
        this.mRemoteDataStore = remoteDataStore;
    }


    @Override
    public void loadVideos(LoadVideosCallback loadVideosCallback) {
        loadVideos(loadVideosCallback,false);
    }


    public void loadVideos(LoadVideosCallback loadVideosCallback,boolean forceUpdate) {

        if(forceUpdate || mVideosItemListCache == null)
        {
           getVideosFromRemoteStore(loadVideosCallback);
        }else
        {
            loadVideosCallback.onVideosLoaded(new ArrayList<VideosItem>(mVideosItemListCache.values()));
        }

    }

    public void loadVideo(LoadVideoItemCallback loadVideoItemCallback,String videoId){

        loadVideoItemCallback.onVideoItemLoaded(mVideosItemListCache.get(videoId));

    }

    private void getVideosFromRemoteStore(LoadVideosCallback loadVideosCallback){


        mRemoteDataStore.loadVideos(new LoadVideosCallback() {
            @Override
            public void onVideosLoaded(List<VideosItem> videosItems) {
                refreshCache(videosItems);
                loadVideosCallback.onVideosLoaded(videosItems);

            }

            @Override
            public void onError(String error) {
                Log.d(TAG,error);
                loadVideosCallback.onError(error);
            }
        });

    }

    private void refreshCache(List<VideosItem> videosItems){

        if(mVideosItemListCache == null)
        {
            mVideosItemListCache = new ArrayMap<>();
        }

        mVideosItemListCache.clear();
        //mVideosItemListCache = videosItems;


        //Map<Integer, VideosItem> appleMap = videosItems.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1, k2)->k1));

        for(VideosItem video : videosItems ){
            mVideosItemListCache.put(video.getVideoId(),video);

        }

    }



}
