package com.example.longyuan.hubtraffic.videodetail;

import com.example.longyuan.hubtraffic.App;
import com.example.longyuan.hubtraffic.datastore.DataStore;
import com.example.longyuan.hubtraffic.datastore.VideoRepository;
import com.example.longyuan.hubtraffic.pojo.video.VideosItem;

import javax.inject.Inject;

/**
 * Created by LONGYUAN on 2018/2/10.
 */

public class VideoDetailPresenter implements VideoDetailContract.Presenter {

    private  VideoDetailContract.View mView;

    private String mVideoId;

    @Inject
    protected VideoRepository mVideoRepository;


    public VideoDetailPresenter(VideoDetailContract.View view,String videoId) {

        mVideoId = videoId;

        mView = view;

        App.getAppComponent().inject(this);

        view.setPresenter(this);
    }

    @Override
    public void start() {

        loadVideoDetail(mVideoId);
    }

    @Override
    public void loadVideoDetail(String videoId) {

        mVideoRepository.loadVideo(new DataStore.LoadVideoItemCallback() {
            @Override
            public void onVideoItemLoaded(VideosItem videosItem) {
                mView.updateData(videosItem);
            }

            @Override
            public void onError(String error) {

            }
        },mVideoId);



    }
}
