package com.example.longyuan.hubtraffic.main;

import com.example.longyuan.hubtraffic.pojo.video.VideosItem;
import com.example.longyuan.hubtraffic.util.enums.SearchOrdering;

import java.util.List;
import java.util.Map;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public interface MainContract {

    interface View{

        void setPresenter(Presenter presenter);

        void updateData(List<VideosItem> videosItemList);

        void errorToast(String error);

    }

    interface Presenter{

        void start(String tag,String category, String starName);

        void loadVideos();

        void loadVideos(SearchOrdering searchOrdering);

        void refreshVideos(Map<String,String> map);

    }
}
