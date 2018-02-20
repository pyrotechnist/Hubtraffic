package com.example.longyuan.hubtraffic.pojo.category;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CategoriesResponse {

	@SerializedName("categories")
	private List<Category> categories;

	public void setCategories(List<Category> categories){
		this.categories = categories;
	}

	public List<Category> getCategories(){
		return categories;
	}

	@Override
 	public String toString(){
		return 
			"CategoriesResponse{" +
			"categories = '" + categories + '\'' + 
			"}";
		}
}