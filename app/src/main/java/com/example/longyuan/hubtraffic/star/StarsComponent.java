package com.example.longyuan.hubtraffic.star;

import com.example.longyuan.hubtraffic.AppComponent;
import com.example.longyuan.hubtraffic.util.FragmentScoped;

import dagger.Component;

/**
 * Created by LONGYUAN on 2018/2/19.
 */

@FragmentScoped
@Component(dependencies = AppComponent.class, modules = StarsModule.class)
public interface StarsComponent {

    void inject(StarsActivity activity);
}
