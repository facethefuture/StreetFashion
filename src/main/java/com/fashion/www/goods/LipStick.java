package com.fashion.www.goods;

public class LipStick {
	private String title;
	private String coverImage;
	private String description;
	public LipStick(String title,String coverImage,String description){
		this.title = title;
		this.coverImage = coverImage;
		this.description = description;
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
}
