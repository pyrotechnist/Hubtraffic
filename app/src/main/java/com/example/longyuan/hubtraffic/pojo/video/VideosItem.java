package com.example.longyuan.hubtraffic.pojo.video;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class VideosItem{

	@SerializedName("thumb")
	private String thumb;

	@SerializedName("rating")
	private String rating;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("tags")
	private List<TagsItem> tags;

	@SerializedName("duration")
	private String duration;

	@SerializedName("ratings")
	private int ratings;

	@SerializedName("segment")
	private String segment;

	@SerializedName("default_thumb")
	private String defaultThumb;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	@SerializedName("publish_date")
	private String publishDate;

	@SerializedName("views")
	private String views;

	@SerializedName("video_id")
	private String videoId;

	@SerializedName("thumbs")
	private List<ThumbsItem> thumbs;

	@SerializedName("pornstars")
	private List<PornstarsItem> pornstars;

	public void setThumb(String thumb){
		this.thumb = thumb;
	}

	public String getThumb(){
		return thumb;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setTags(List<TagsItem> tags){
		this.tags = tags;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return duration;
	}

	public void setRatings(int ratings){
		this.ratings = ratings;
	}

	public int getRatings(){
		return ratings;
	}

	public void setSegment(String segment){
		this.segment = segment;
	}

	public String getSegment(){
		return segment;
	}

	public void setDefaultThumb(String defaultThumb){
		this.defaultThumb = defaultThumb;
	}

	public String getDefaultThumb(){
		return defaultThumb;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public void setPublishDate(String publishDate){
		this.publishDate = publishDate;
	}

	public String getPublishDate(){
		return publishDate;
	}

	public void setViews(String views){
		this.views = views;
	}

	public String getViews(){
		return views;
	}

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return videoId;
	}

	public void setThumbs(List<ThumbsItem> thumbs){
		this.thumbs = thumbs;
	}

	public List<ThumbsItem> getThumbs(){
		return thumbs;
	}

	public void setPornstars(List<PornstarsItem> pornstars){
		this.pornstars = pornstars;
	}

	public List<PornstarsItem> getPornstars(){
		return pornstars;
	}

	@Override
 	public String toString(){
		return 
			"VideosItem{" + 
			"thumb = '" + thumb + '\'' + 
			",rating = '" + rating + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			",tags = '" + tags + '\'' + 
			",duration = '" + duration + '\'' + 
			",ratings = '" + ratings + '\'' + 
			",segment = '" + segment + '\'' + 
			",default_thumb = '" + defaultThumb + '\'' + 
			",categories = '" + categories + '\'' + 
			",publish_date = '" + publishDate + '\'' + 
			",views = '" + views + '\'' + 
			",video_id = '" + videoId + '\'' + 
			",thumbs = '" + thumbs + '\'' + 
			",pornstars = '" + pornstars + '\'' + 
			"}";
		}
}