package com.example.longyuan.hubtraffic.videodetail;

import com.example.longyuan.hubtraffic.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LONGYUAN on 2018/2/10.
 */

@Module
public class VideoDetailModule {


    private final VideoDetailContract.View mView;

    private String mVideoId;

    public VideoDetailModule(VideoDetailContract.View view, String videoId) {
        mView = view;

        mVideoId = videoId;
    }

/*    @Provides
    MainContract.View provideUniversityContractView() {
        return mView;
    }*/

    @Provides
    VideoDetailContract.Presenter provideVideoDetailContractPresenter() {
        return new VideoDetailPresenter(mView,mVideoId);
    }
}
