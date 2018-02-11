package com.example.longyuan.hubtraffic.videodetail;

import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import java.util.List;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public interface VideoDetailContract {

    interface View{

        void setPresenter(Presenter presenter);

        void updateData(VideosItem videosItem);

    }

    interface Presenter{

        void start();

        void loadVideoDetail(String videoId);

    }
}
