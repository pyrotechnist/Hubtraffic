package com.example.longyuan.hubtraffic.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

@Module
public class MainModule {

    private final MainContract.View mView;

    public MainModule(MainContract.View view) {
        mView = view;
    }

/*    @Provides
    MainContract.View provideUniversityContractView() {
        return mView;
    }*/

    @Provides
    MainContract.Presenter provideMainContractPresenter() {
        return new MainPresenter(mView);
    }
}