package com.fashion.www.goods;

public class Goods {
	private int id;
	private String title;
	private String coverImage;
	private String description;
	private String tags;
	private int createdTime;
	private String enable;
	public Goods(int id,String title,String coverImage,String description,String tags,int createdTime,String enable){
		this.id = id;
		this.title = title;
		this.coverImage = coverImage;
		this.description = description;
		this.tags = tags;
		this.createdTime = createdTime;
		this.enable = enable;
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
	public int getCreatedTime(){
		return this.createdTime;
	}
	public String getEnable(){
		return this.enable;
	}
}
