package com.example.longyuan.hubtraffic.pojo.video;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TagsItem{

	@SerializedName("tag_name")
	private String tagName;

	public void setTagName(String tagName){
		this.tagName = tagName;
	}

	public String getTagName(){
		return tagName;
	}

	@Override
 	public String toString(){
		return 
			"TagsItem{" + 
			"tag_name = '" + tagName + '\'' + 
			"}";
		}
}