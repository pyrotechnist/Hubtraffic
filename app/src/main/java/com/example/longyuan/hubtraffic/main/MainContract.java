package com.example.longyuan.hubtraffic.main;

import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import java.util.List;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public interface MainContract {

    interface View{

        void setPresnter(Presenter presnter);

        void updateData(List<VideosItem> videosItemList);

        void errorToast(String error);

    }

    interface Presenter{

        void start();

        void loadVideos();

        void refreshVideos();

    }
}
