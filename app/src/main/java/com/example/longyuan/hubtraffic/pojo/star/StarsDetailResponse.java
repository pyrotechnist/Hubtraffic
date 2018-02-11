package com.example.longyuan.hubtraffic.pojo.star;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class StarsDetailResponse{

	@SerializedName("stars")
	private List<StarsItem> stars;

	public void setStars(List<StarsItem> stars){
		this.stars = stars;
	}

	public List<StarsItem> getStars(){
		return stars;
	}

	@Override
 	public String toString(){
		return 
			"StarsDetailResponse{" + 
			"stars = '" + stars + '\'' + 
			"}";
		}
}