package com.example.longyuan.hubtraffic.network.api;

import com.example.longyuan.hubtraffic.pojo.category.CategoriesResponse;
import com.example.longyuan.hubtraffic.pojo.star.StarsDetailResponse;
import com.example.longyuan.hubtraffic.pojo.video.VideosResponse;

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

    @GET("stars_detailed")
    Observable<StarsDetailResponse> getStarsDetail();

    @GET("categories")
    Observable<CategoriesResponse> getCategories();

}
