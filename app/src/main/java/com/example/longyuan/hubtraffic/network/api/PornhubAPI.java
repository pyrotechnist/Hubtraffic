package com.example.longyuan.hubtraffic.network.api;

import com.example.longyuan.hubtraffic.pojo.video.VideosResponse;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by LONGYUAN on 2018/2/7.
 */

public interface PornhubAPI {

    @GET("search")
    Observable<VideosResponse> getVideos(@QueryMap Map<String, String> options);

}
