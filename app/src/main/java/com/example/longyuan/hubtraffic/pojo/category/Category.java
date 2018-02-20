package com.example.longyuan.hubtraffic.pojo.category;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Category {

	@SerializedName("id")
	private String id;

	@SerializedName("category")
	private String category;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"Category{" +
			"id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			"}";
		}
}