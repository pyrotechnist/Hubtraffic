package com.example.longyuan.hubtraffic.pojo.video;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CategoriesItem{

	@SerializedName("category")
	private String category;

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"CategoriesItem{" + 
			"category = '" + category + '\'' + 
			"}";
		}
}