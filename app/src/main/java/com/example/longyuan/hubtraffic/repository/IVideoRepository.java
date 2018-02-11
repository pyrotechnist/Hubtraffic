package com.example.longyuan.hubtraffic.repository;

import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import java.util.List;

/**
 * Created by LONGYUAN on 2018/2/10.
 */

public interface IVideoRepository {

    interface LoadVideosCallback {

        void onVideosLoaded(List<VideosItem> universities);

    }

    void LoadVideos(LoadVideosCallback loadVideosCallback);

}
