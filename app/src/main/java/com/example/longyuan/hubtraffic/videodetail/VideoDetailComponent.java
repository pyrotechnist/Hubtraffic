package com.example.longyuan.hubtraffic.videodetail;

import com.example.longyuan.hubtraffic.AppComponent;
import com.example.longyuan.hubtraffic.util.FragmentScoped;

import dagger.Component;

/**
 * Created by LONGYUAN on 2018/2/10.
 */


@FragmentScoped
@Component(dependencies = AppComponent.class, modules = VideoDetailModule.class)
public interface VideoDetailComponent {

    void inject(VideoDetailActivity activity);

}
