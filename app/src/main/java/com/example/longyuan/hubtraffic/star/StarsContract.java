package com.example.longyuan.hubtraffic.star;

import com.example.longyuan.hubtraffic.pojo.star.StarsItem;

import java.util.List;

/**
 * Created by LONGYUAN on 2018/2/19.
 */

public interface StarsContract {

    interface View{

        void setPresenter(Presenter presenter);

        void updateData(List<StarsItem> starsItemList);

    }

    interface Presenter{

        void start();



    }
}
