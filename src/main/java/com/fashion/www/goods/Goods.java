package com.fashion.www.goods;

public class Goods {
	private int id;
	private String title;
	private String coverImage;
	private String description;
	private String tags;
	public Goods(int id,String title,String coverImage,String description,String tags){
		this.id = id;
		this.title = title;
		this.coverImage = coverImage;
		this.description = description;
		this.tags = tags;
	}
	public int getId(){
		return this.id;
	}
	public String getTitle(){
		return this.title;
	}
	public String getCoverImage(){
		return this.coverImage;
	}
	public String getDescription(){
		return this.description;
	}
	public String getTags(){
		return this.tags;
	}
}
