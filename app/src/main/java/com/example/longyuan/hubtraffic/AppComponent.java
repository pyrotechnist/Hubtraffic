package com.example.longyuan.hubtraffic;

import com.example.longyuan.hubtraffic.datastore.RepositoryModule;
import com.example.longyuan.hubtraffic.datastore.remote.RemoteDataStore;
import com.example.longyuan.hubtraffic.main.MainPresenter;
import com.example.longyuan.hubtraffic.network.injection.NetworkModule;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailContract;
import com.example.longyuan.hubtraffic.videodetail.VideoDetailPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class})
public interface AppComponent {

    void inject(MainPresenter mainPresenter);

    void inject(VideoDetailPresenter videoDetailPresenter);

    void inject(RemoteDataStore remoteDataStore);
}
