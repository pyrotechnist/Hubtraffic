package com.example.longyuan.hubtraffic;

import com.example.longyuan.hubtraffic.category.CategoryActivity;
import com.example.longyuan.hubtraffic.datastore.RepositoryModule;
import com.example.longyuan.hubtraffic.datastore.remote.RemoteDataStore;
import com.example.longyuan.hubtraffic.main.MainPresenter;
import com.example.longyuan.hubtraffic.network.injection.NetworkModule;
import com.example.longyuan.hubtraffic.star.StarsPresenter;
import com.example.longyuan.hubtraffic.stardetail.StarDetailActivity;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailContract;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailPresenter;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailTabInfFragment;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailTabVideosFragment;
import com.example.longyuan.hubtraffic.welcome.WelcomeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class})
public interface AppComponent {

    void inject(MainPresenter mainPresenter);

    void inject(VideoDetailTabVideosFragment videoDetailTabVideosFragment);

    void inject(VideoDetailTabInfFragment videoDetailTabInfFragment);

    void inject(VideoDetailPresenter videoDetailPresenter);

    void inject(StarsPresenter starsPresenter);

    void inject(WelcomeActivity welcomeActivity);

    void inject(CategoryActivity categoryActivity);

    void inject(StarDetailActivity starDetailActivity);

    void inject(RemoteDataStore remoteDataStore);
}
