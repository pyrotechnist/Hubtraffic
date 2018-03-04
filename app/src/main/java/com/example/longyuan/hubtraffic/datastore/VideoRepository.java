package com.example.longyuan.hubtraffic.datastore;

import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.local.LocalDataStore;
import com.example.longyuan.hubtraffic.datastore.remote.RemoteDataStore;
import com.example.longyuan.hubtraffic.pojo.category.Category;
import com.example.longyuan.hubtraffic.pojo.star.StarsItem;
import com.example.longyuan.hubtraffic.pojo.video.TagsItem;
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

    private ArrayMap<String,StarsItem> mStarListCache;

    private List<Category> mCategoriesCache;

   // private List<StarsItem> mStarsCache;

    private LoadVideosCallback mLoadVideosCallback;

    @Inject
    public VideoRepository(RemoteDataStore remoteDataStore, LocalDataStore localDataStore){
        this.mLocalDataStore = localDataStore;
        this.mRemoteDataStore = remoteDataStore;
    }


    @Override
    public void loadVideos(LoadVideosCallback loadVideosCallback) {
        loadVideos(loadVideosCallback,null,false);
    }

    public void loadVideos(LoadVideosCallback loadVideosCallback,Map<String,String> map) {
        loadVideos(loadVideosCallback,map,true);
    }

    public void loadVideos(LoadVideosCallback loadVideosCallback,Map<String,String> map,boolean forceUpdate) {

        if(forceUpdate || mVideosItemListCache == null)
        {
           getVideosFromRemoteStore(loadVideosCallback,map);
        }else
        {
            loadVideosCallback.onVideosLoaded(new ArrayList<VideosItem>(mVideosItemListCache.values()));
        }

    }


    /*

     */
    public void loadVideos(LoadVideosCallback loadVideosCallback,boolean forceUpdate,String videoId) {

        List<TagsItem> tagList = mVideosItemListCache.get(videoId).getTags();



    }



    public void loadVideo(LoadVideoItemCallback loadVideoItemCallback,String videoId){

        loadVideoItemCallback.onVideoItemLoaded(mVideosItemListCache.get(videoId));

    }

    private void getVideosFromRemoteStore(LoadVideosCallback loadVideosCallback,Map map){


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
        },map);

    }

    private void refreshCache(List<VideosItem> videosItems){

        if(mVideosItemListCache == null)
        {
            mVideosItemListCache = new ArrayMap<>();
        }

        mVideosItemListCache.clear();

        for(VideosItem video : videosItems ){
            mVideosItemListCache.put(video.getVideoId(),video);

        }

    }

    public void loadStars(LoadStarsCallback loadStarsCallback){

        if(mStarListCache == null || mStarListCache.size()==0)
        {

            mRemoteDataStore.loadStars(new LoadStarsCallback() {
                @Override
                public void onStarsLoaded(List<StarsItem> starsItems) {
                    refreshStarCache(starsItems);
                    loadStarsCallback.onStarsLoaded(starsItems);
                }

                @Override
                public void onError(String error) {

                }
            });
        }else {

            loadStarsCallback.onStarsLoaded(new ArrayList<StarsItem>(mStarListCache.values()));
        }
    }

    private void refreshStarCache(List<StarsItem> starsItems){

        if(mStarListCache == null)
        {
            mStarListCache = new ArrayMap<>();
        }

        mStarListCache.clear();

        for(StarsItem star : starsItems ){
            mStarListCache.put(star.getStar().getStarName(),star);

        }

    }


    public void loadStarDetail(LoadStarItemCallback loadStarItemCallback,String starName){

        loadStarItemCallback.onStarItemLoaded(mStarListCache.get(starName));
    }

    public void loadCategories(LoadCategoriesCallback loadCategoriesCallback){

        if(mCategoriesCache == null || mCategoriesCache.size()==0)
        {

            mCategoriesCache = new ArrayList<>();
            mRemoteDataStore.loadCategories(new LoadCategoriesCallback() {
                @Override
                public void onCategoriesLoaded(List<Category> categories) {
                    mCategoriesCache = categories;
                    loadCategoriesCallback.onCategoriesLoaded(categories);

                }

                @Override
                public void onError(String error) {

                }
            });

        }else {

            loadCategoriesCallback.onCategoriesLoaded(mCategoriesCache);
        }

    }


}
