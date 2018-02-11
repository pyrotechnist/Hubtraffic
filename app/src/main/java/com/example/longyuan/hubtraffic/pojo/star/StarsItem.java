package com.example.longyuan.hubtraffic.pojo.star;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class StarsItem{

	@SerializedName("star")
	private Star star;

	public void setStar(Star star){
		this.star = star;
	}

	public Star getStar(){
		return star;
	}

	@Override
 	public String toString(){
		return 
			"StarsItem{" + 
			"star = '" + star + '\'' + 
			"}";
		}
}