package com.example.longyuan.hubtraffic;

import android.app.Application;

import com.example.longyuan.hubtraffic.datastore.RepositoryModule;
import com.example.longyuan.hubtraffic.network.injection.NetworkModule;
import com.example.longyuan.hubtraffic.util.Constant;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public class App extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule(Constant.ROOT_URL))
               // .repositoryModule(new RepositoryModule())
                .build();

    }

    public static AppComponent getAppComponent() {

        return  mAppComponent;
    }
}
