package com.example.longyuan.hubtraffic.datastore;

import com.example.longyuan.hubtraffic.pojo.category.CategoriesResponse;
import com.example.longyuan.hubtraffic.pojo.category.Category;
import com.example.longyuan.hubtraffic.pojo.star.StarsItem;
import com.example.longyuan.hubtraffic.pojo.video.CategoriesItem;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import java.util.List;

/**
 * Created by LONGYUAN on 2018/2/10.
 */

public interface DataStore {

    interface LoadVideosCallback {

        void onVideosLoaded(List<VideosItem> videosItems);

         void onError(String error);

    }


    interface LoadStarsCallback {

        void onStarsLoaded(List<StarsItem> starsItems);

        void onError(String error);

    }

    interface LoadStarItemCallback {

        void onStarItemLoaded(StarsItem starsItem);

        void onError(String error);

    }


    interface LoadVideoItemCallback {

        void onVideoItemLoaded(VideosItem videosItem);

        void onError(String error);

    }

    interface LoadCategoriesCallback {

        void onCategoriesLoaded(List<Category> categories);

        void onError(String error);

    }

    void loadVideos(LoadVideosCallback loadVideosCallback);
}
