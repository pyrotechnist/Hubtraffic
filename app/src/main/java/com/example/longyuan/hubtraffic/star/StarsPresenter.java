package com.example.longyuan.hubtraffic.star;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.VideoRepository;
import com.example.longyuan.hubtraffic.pojo.star.StarsItem;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by LONGYUAN on 2018/2/19.
 */

public class StarsPresenter implements StarsContract.Presenter {


    private final static String TAG = StarsPresenter.class.getSimpleName();

    private StarsContract.View mView;

    @Inject
    protected VideoRepository mVideoRepository;


    public StarsPresenter(StarsContract.View view) {

        mView = view;

        App.getAppComponent().inject(this);

        view.setPresenter(this);
    }

    @Override
    public void start() {

        loadStars();

    }

    private void loadStars() {
        mVideoRepository.loadStars(new DataStore.LoadStarsCallback() {
            @Override
            public void onStarsLoaded(List<StarsItem> starsItems) {

                mView.updateData(starsItems);
            }

            @Override
            public void onError(String error) {

            }
        });

    }


}
