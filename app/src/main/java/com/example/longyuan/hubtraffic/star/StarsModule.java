package com.example.longyuan.hubtraffic.star;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LONGYUAN on 2018/2/19.
 */

@Module
public class StarsModule {

    private final StarsContract.View mView;

    public StarsModule(StarsContract.View view) {
        mView = view;
    }

/*    @Provides
    MainContract.View provideUniversityContractView() {
        return mView;
    }*/

    @Provides
    StarsContract.Presenter provideStarsContractPresenter() {
        return new StarsPresenter(mView);
    }
}
