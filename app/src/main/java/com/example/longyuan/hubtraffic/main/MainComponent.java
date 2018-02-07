package com.example.longyuan.hubtraffic.main;

import com.example.longyuan.hubtraffic.AppComponent;
import com.example.longyuan.hubtraffic.util.FragmentScoped;

import dagger.Component;

/**
 * Created by LONGYUAN on 2018/2/7.
 */


@FragmentScoped
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity activity);

}
