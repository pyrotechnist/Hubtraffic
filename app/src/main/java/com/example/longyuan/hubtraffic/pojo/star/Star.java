package com.example.longyuan.hubtraffic.pojo.star;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Star{

	@SerializedName("star_name")
	private String starName;

	@SerializedName("star_thumb")
	private String starThumb;

	@SerializedName("star_url")
	private String starUrl;

	@SerializedName("videos_count_all")
	private String videosCount;

	@SerializedName("gender")
	private String gender;

	public String getVideosCount() {
		return videosCount;
	}

	public void setVideosCount(String videosCount) {
		this.videosCount = videosCount;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setStarName(String starName){
		this.starName = starName;
	}

	public String getStarName(){
		return starName;
	}

	public void setStarThumb(String starThumb){
		this.starThumb = starThumb;
	}

	public String getStarThumb(){
		return starThumb;
	}

	public void setStarUrl(String starUrl){
		this.starUrl = starUrl;
	}

	public String getStarUrl(){
		return starUrl;
	}

	@Override
 	public String toString(){
		return 
			"Star{" + 
			"star_name = '" + starName + '\'' + 
			",star_thumb = '" + starThumb + '\'' + 
			",star_url = '" + starUrl + '\'' + 
			"}";
		}
}