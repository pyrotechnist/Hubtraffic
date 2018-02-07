package com.example.longyuan.hubtraffic.pojo.video;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class VideosResponse{

	@SerializedName("videos")
	private List<VideosItem> videos;

	public void setVideos(List<VideosItem> videos){
		this.videos = videos;
	}

	public List<VideosItem> getVideos(){
		return videos;
	}

	@Override
 	public String toString(){
		return 
			"VideosResponse{" + 
			"videos = '" + videos + '\'' + 
			"}";
		}
}