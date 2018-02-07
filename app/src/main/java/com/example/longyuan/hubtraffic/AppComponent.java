package com.example.longyuan.hubtraffic;

import com.example.longyuan.hubtraffic.main.MainPresenter;
import com.example.longyuan.hubtraffic.network.injection.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(MainPresenter mainPresenter);
}
